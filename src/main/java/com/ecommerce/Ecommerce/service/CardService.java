package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.request.CardRequestDto;
import com.ecommerce.Ecommerce.dto.response.CardResponseDto;
import org.springframework.web.bind.annotation.RequestParam;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto);
    public String cardMask(String cardNumber);

    public CardResponseDto updateCardNumber(String oldCardNo, String newCardNo);
    public String deleteCard(String cardNo);
}
