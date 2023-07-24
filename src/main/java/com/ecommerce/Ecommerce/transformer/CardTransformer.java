package com.ecommerce.Ecommerce.transformer;

import com.ecommerce.Ecommerce.Enum.CardType;
import com.ecommerce.Ecommerce.dto.request.CardRequestDto;
import com.ecommerce.Ecommerce.dto.response.CardResponseDto;
import com.ecommerce.Ecommerce.model.Card;

import java.sql.Date;

public class CardTransformer {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto) {
        return Card.builder()
                .cardNo(cardRequestDto.getCardNumber())
                .cardType(cardRequestDto.getCardType())
                .validTill((Date) cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
    public static CardResponseDto cardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .cardType(card.getCardType())
                .validTill(card.getValidTill())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
