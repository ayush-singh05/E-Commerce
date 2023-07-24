package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.Exception.CustomerNotFoundException;
import com.ecommerce.Ecommerce.dto.request.CardRequestDto;
import com.ecommerce.Ecommerce.dto.response.CardResponseDto;
import com.ecommerce.Ecommerce.model.Card;
import com.ecommerce.Ecommerce.model.Customer;
import com.ecommerce.Ecommerce.repository.CustomerRepository;
import com.ecommerce.Ecommerce.service.CardService;
import com.ecommerce.Ecommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CardServiceImp implements CardService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {
        Customer customer = customerRepository.findByMobile(cardRequestDto.getMobile());
        if(customer == null){
            throw new CustomerNotFoundException("Customer Not Found");
        }
        // DTO --> Entity
        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);
        List<Card>cardList = savedCustomer.getCards();
        Card latestCard = cardList.get(cardList.size()-1);
        CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(latestCard);
        cardResponseDto.setCardNo(cardMask(latestCard.getCardNo()));
        return cardResponseDto;

    }
    public String cardMask(String cardNumber) {
        int cardLength = cardNumber.length();
        String maskedCard = "";
        for(int i = 0;i<cardLength-4;i++){
            maskedCard += 'X';
        }

        maskedCard += cardNumber.substring(cardLength-4);
        return maskedCard;
    }
}
