package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.dto.request.SellerRequestDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.dto.response.SellerResponseDto;
import com.ecommerce.Ecommerce.model.Product;
import com.ecommerce.Ecommerce.model.Seller;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.repository.SellerRepository;
import com.ecommerce.Ecommerce.service.SellerService;
import com.ecommerce.Ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImp implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        // DTO --> Entity
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        // Entity --> Response
        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }


}
