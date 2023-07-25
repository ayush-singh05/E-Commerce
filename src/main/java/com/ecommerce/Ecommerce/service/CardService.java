package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.request.CardRequestDto;
import com.ecommerce.Ecommerce.dto.response.CardResponseDto;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto);
    public String cardMask(String cardNumber);
}
