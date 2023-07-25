package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.Exception.CustomerNotFoundException;
import com.ecommerce.Ecommerce.Exception.EmptyCartException;
import com.ecommerce.Ecommerce.Exception.InvalidCardDetailsException;
import com.ecommerce.Ecommerce.dto.request.CheckOutCartRequestDto;
import com.ecommerce.Ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.Ecommerce.dto.response.CartResponseDto;
import com.ecommerce.Ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.Ecommerce.model.*;
import com.ecommerce.Ecommerce.repository.*;
import com.ecommerce.Ecommerce.service.CartService;
import com.ecommerce.Ecommerce.service.OrderService;
import com.ecommerce.Ecommerce.transformer.CartTransformer;
import com.ecommerce.Ecommerce.transformer.OrderTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImp implements CartService {
    Logger logger = LoggerFactory.getLogger(CartServiceImp.class);
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderEntityRepository orderEntityRepository;
    @Override
    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item) {
        Customer customer = customerRepository.findByEmail(itemRequestDto.getCustomerEmail());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+product.getPrice() * itemRequestDto.getRequiredQuantity());

        item.setCart(cart);
        item.setProduct(product);
        Item saveItem = itemRepository.save(item);

        cart.getItems().add(item);
        product.getItems().add(saveItem);
        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);
        return CartTransformer.CartToCartResponseDto(cart);

    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) {
        Customer customer = customerRepository.findByEmail(checkOutCartRequestDto.getCustomerEmail());
        if(customer == null) {
            throw new CustomerNotFoundException("Error : Invalid Customer !");
        }
        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());

        Date currDate = new Date();
        if(card == null || checkOutCartRequestDto.getCvv() != card.getCvv() || currDate.after(card.getValidTill())) {
            throw new InvalidCardDetailsException("Error : Invalid Card Details !");
        }
        Cart cart = customer.getCart();
        if (cart.getItems().isEmpty()) {
            throw new EmptyCartException("Error : No item in cart");
        }
        OrderEntity orderEntity = orderService.placeOrder(cart,card);
        resetCart(cart);
        OrderEntity saveOrder = orderEntityRepository.save(orderEntity);

        return OrderTransformer.orderToOrderResponseDto(saveOrder);

    }
    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());

    }
}
