package com.example.shoppingworld.Repository;

import com.example.shoppingworld.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
    public Card findByCardNo(String cardNo);
}
