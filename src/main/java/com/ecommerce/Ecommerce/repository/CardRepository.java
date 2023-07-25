package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
    public Card findByCardNo(String cardNo);
}
