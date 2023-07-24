package com.ecommerce.Ecommerce.transformer;

import com.ecommerce.Ecommerce.dto.request.SellerRequestDto;
import com.ecommerce.Ecommerce.dto.response.SellerResponseDto;
import com.ecommerce.Ecommerce.model.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto) {
        return Seller.builder()
                .emailId(sellerRequestDto.getEmail())
                .name(sellerRequestDto.getName())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }
    public static SellerResponseDto sellerToSellerResponseDto(Seller seller) {
        return SellerResponseDto.builder()
                .name(seller.getName())
                .panNo(seller.getPanNo())
                .build();
    }
}
