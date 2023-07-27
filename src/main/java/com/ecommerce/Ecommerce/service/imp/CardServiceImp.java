package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.Exception.CustomerNotFoundException;
import com.ecommerce.Ecommerce.Exception.InvalidCardNumberException;
import com.ecommerce.Ecommerce.dto.request.CardRequestDto;
import com.ecommerce.Ecommerce.dto.response.CardResponseDto;
import com.ecommerce.Ecommerce.model.Card;
import com.ecommerce.Ecommerce.model.Customer;
import com.ecommerce.Ecommerce.repository.CardRepository;
import com.ecommerce.Ecommerce.repository.CustomerRepository;
import com.ecommerce.Ecommerce.service.CardService;
import com.ecommerce.Ecommerce.transformer.CardTransformer;
import com.ecommerce.Ecommerce.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CardServiceImp implements CardService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

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

    @Override
    public CardResponseDto updateCardNumber(String oldCardNo, String newCardNo) {
        Card card = cardRepository.findByCardNo(oldCardNo);
        if(card == null){
            throw new InvalidCardNumberException("Invalid Card Number");
        }
        if(oldCardNo.equals(newCardNo)) {
            throw new InvalidCardNumberException("The card numbers provided are same. Please check the card numbers and try again.");
        }
        Customer customer = card.getCustomer();
        card.setCardNo(newCardNo);
        customer.getCards().add(card);
        customerRepository.save(customer);
        CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(customer.getCards().get(customer.getCards().size()-1));
        cardResponseDto.setCardNo(cardMask(card.getCardNo()));
        return cardResponseDto;
    }

    @Override
    public String deleteCard(String cardNo) {
        Card card = cardRepository.findByCardNo(cardNo);
        if(card == null){
            throw new InvalidCardNumberException("Invalid Card Number");
        }
        cardRepository.delete(card);

        return new String("You have successfully delete you card");
    }
}
