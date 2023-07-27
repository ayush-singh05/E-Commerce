package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.Enum.OrderStatus;
import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.Exception.InsufficientQuantityException;
import com.ecommerce.Ecommerce.Exception.OrderAlreadyCancelled;
import com.ecommerce.Ecommerce.Exception.OrderNotFoundException;
import com.ecommerce.Ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.model.*;
import com.ecommerce.Ecommerce.repository.CustomerRepository;
import com.ecommerce.Ecommerce.repository.OrderEntityRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.service.CardService;
import com.ecommerce.Ecommerce.service.OrderService;
import com.ecommerce.Ecommerce.transformer.OrderTransformer;
import com.ecommerce.Ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    CardService cardService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public OrderEntity placeOrder(Cart cart, Card card) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.cardMask(card.getCardNo()));

        int orderTotal = 0;
        String itemName = "";
        for(Item item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.getAvailableQuantity() < item.getRequiredQuantity()) {
                throw new InsufficientQuantityException("Error : Insufficient Quantity available for "+ product.getProductName());
            }
            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            orderTotal += product.getPrice() * item.getRequiredQuantity();
            item.setOrderEntity(orderEntity);
            itemName += item.getProduct().getProductName()+" ";
        }
        // update quantity
        orderEntity.setOrderTotal(orderTotal);

        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(card.getCustomer());
        orderEntity.setStatus(OrderStatus.PENDING);

        // Email implementation
        String message = "Hi "+orderEntity.getCustomer().getName()+"\n"+
                        "Your order has been successfully placed,Your order details are "+itemName;
        String subject = "Your order for "+itemName+" "+"successfully placed";
        sendEmail(orderEntity.getCustomer().getEmail(),message,subject);

        // return response
        return orderEntity;

    }

    @Override
    public List<OrderResponseDto> topOrder(int noOfOrder) {
        PageRequest pageRequest = PageRequest.of(0, noOfOrder, Sort.by(Sort.Direction.DESC, "orderTotal"));


        List<OrderEntity> topOrders = orderEntityRepository.findAll(pageRequest).getContent();


        List<OrderEntity> list = orderEntityRepository.findAll();

        List<OrderResponseDto> responseOrder = new ArrayList<>();

        for(OrderEntity oe : topOrders) {

            responseOrder.add(OrderTransformer.orderToOrderResponseDto(oe));

        }
        return responseOrder;


    }

    @Override
    public List<ProductResponseDto> cheapestOrder(ProductCategory category,int noOfProduct) {
        PageRequest pageRequest = PageRequest.of(0, noOfProduct, Sort.by(Sort.Direction.ASC, "price"));
        List<Product> topOrders = productRepository.findAll(pageRequest).getContent();
        List<ProductResponseDto> responseOrder = new ArrayList<>();
        for(Product pr : topOrders) {
            if(pr.getCategory().equals(category)){
                responseOrder.add(ProductTransformer.productToProductResponseDto(pr));
            }
        }
        return responseOrder;
    }

    @Override
    public OrderResponseDto cancelOrder(String orderId) {
        OrderEntity orderEntity = orderEntityRepository.findByOrderId(orderId); // orderId
        // Exception
        if(orderEntity == null){
            throw new OrderNotFoundException("Error : Invalid Order ID");
        }
        if(orderEntity.getStatus().equals(OrderStatus.CANCEL)){
            throw new OrderAlreadyCancelled("Error : Your order has already been canceled");
        }

        orderEntity.setStatus(OrderStatus.CANCEL);
        Customer customer = orderEntity.getCustomer();
        customer.getOrders().add(orderEntity);
        customerRepository.save(customer); // save parent
        OrderResponseDto orderTransformer = OrderTransformer.orderToOrderResponseDto(orderEntity);

        // Email implementation
        String message = "Hi "+customer.getName()+"\n"+"Based on your request,the cancellation of the below listed item from your order "+orderId+" is being processed by seller."+
                "\n"+orderTransformer;
        String subject = "Order Cancellation";
        sendEmail(orderEntity.getCustomer().getEmail(),message,subject);

        return orderTransformer;

    }
    public void sendEmail(String emailId,String message,String subject) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("akashsingh2442@gmail.com");
        simpleMailMessage.setTo(emailId);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);


    }
}
