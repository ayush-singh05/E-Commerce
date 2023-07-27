package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.request.SellerRequestDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.dto.response.SellerResponseDto;

import java.util.List;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

}
