package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.Ecommerce.dto.response.CartResponseDto;
import com.ecommerce.Ecommerce.model.Cart;
import com.ecommerce.Ecommerce.model.Customer;
import com.ecommerce.Ecommerce.model.Item;
import com.ecommerce.Ecommerce.model.Product;
import com.ecommerce.Ecommerce.repository.CartRepository;
import com.ecommerce.Ecommerce.repository.CustomerRepository;
import com.ecommerce.Ecommerce.repository.ItemRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.service.CartService;
import com.ecommerce.Ecommerce.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;
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
}
