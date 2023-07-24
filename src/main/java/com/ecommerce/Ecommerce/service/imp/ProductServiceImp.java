package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.Exception.SellerNotFoundException;
import com.ecommerce.Ecommerce.dto.request.ProductRequestDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.model.Product;
import com.ecommerce.Ecommerce.model.Seller;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.repository.SellerRepository;
import com.ecommerce.Ecommerce.service.ProductService;
import com.ecommerce.Ecommerce.transformer.ProductTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
       Seller seller = sellerRepository.findById(productRequestDto.getSellerId()).get();

       if(seller == null) {
            throw  new SellerNotFoundException("Invalid Seller ID !!");
        }

        Product product = ProductTransformer.productRequestToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller);
        List<Product> productList = savedSeller.getProducts();
        Product latestProduct = productList.get(productList.size()-1);


        return ProductTransformer.productToProductResponseDto(latestProduct);
    }

    @Override
    public List<ProductResponseDto> getProductByCategoryAndPriceGreaterThan(ProductCategory productCategory, int price) {

        List<Product> list = productRepository.findByCategoryAndPrice(productCategory,price);
        List<ProductResponseDto> productList = new ArrayList<>();

        for(Product pr : list) {
            productList.add(ProductTransformer.productToProductResponseDto(pr));
        }
        return productList;
    }
}
