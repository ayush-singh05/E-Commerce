package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.Exception.CustomerNotFoundException;
import com.ecommerce.Ecommerce.Exception.EmailNotFoundException;
import com.ecommerce.Ecommerce.Exception.InsufficientQuantityException;
import com.ecommerce.Ecommerce.Exception.ProductNotFoundException;
import com.ecommerce.Ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.Ecommerce.model.Customer;
import com.ecommerce.Ecommerce.model.Item;
import com.ecommerce.Ecommerce.model.Product;
import com.ecommerce.Ecommerce.repository.CustomerRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.service.ItemService;
import com.ecommerce.Ecommerce.transformer.ItemTransformer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemServiceImp implements ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
//    Logger logger = LoggerFactory.getLogger();
    @Override
    public Item createItem(ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findByEmail(itemRequestDto.getCustomerEmail());
        if(customer == null){
            throw new EmailNotFoundException("Error : Email Not Found");
        }
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();
        if (product == null){
            throw new ProductNotFoundException("Error : Product doesn't Exist");
        }
        if (product.getAvailableQuantity() < itemRequestDto.getRequiredQuantity()) {
            throw new InsufficientQuantityException("Error : Invalid Quantity !");
        }
        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
