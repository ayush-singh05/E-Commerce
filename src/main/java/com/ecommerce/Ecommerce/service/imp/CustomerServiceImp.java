package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.dto.request.CustomerRequestDto;
import com.ecommerce.Ecommerce.dto.response.CustomerResponseDto;
import com.ecommerce.Ecommerce.model.Cart;
import com.ecommerce.Ecommerce.model.Customer;
import com.ecommerce.Ecommerce.repository.CustomerRepository;
import com.ecommerce.Ecommerce.service.CustomerService;
import com.ecommerce.Ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCartTotal(0);
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }
}
