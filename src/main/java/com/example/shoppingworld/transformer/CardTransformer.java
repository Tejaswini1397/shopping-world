package com.example.shoppingworld.transformer;

import com.example.shoppingworld.Model.Card;
import com.example.shoppingworld.dto.RequestDto.CardRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CardResponseDto;

public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
    public static CardResponseDto CardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .validTill(card.getValidTill())
                .cardType(card.getCardType())
                .build();
    }
}
