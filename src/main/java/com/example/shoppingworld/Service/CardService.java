package com.example.shoppingworld.Service;

import com.example.shoppingworld.Model.Card;
import com.example.shoppingworld.Model.Customer;
import com.example.shoppingworld.Repository.CustomerRepository;
import com.example.shoppingworld.dto.RequestDto.CardRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CardResponseDto;
import com.example.shoppingworld.exception.CustomerNotFoundException;
import com.example.shoppingworld.transformer.CardTransformer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto){
        Customer customer=customerRepository.findByMobileNo(cardRequestDto.getCustomerMobile());
        if(customer==null){
            throw new CustomerNotFoundException("Customer Doesn't Exist");
        }
        //create card entity
       // System.out.println(cardRequestDto.getValidTill());
        Card card= CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCardList().add(card);
        card.setValidTill(cardRequestDto.getValidTill());

        customerRepository.save(customer);
        List<Card> cardList=customer.getCardList();
        Card latestCard=cardList.get(cardList.size()-1);

        CardResponseDto cardResponseDto=CardTransformer.CardToCardResponseDto(latestCard);
        cardResponseDto.setCardNo(generatedMaskedCard(latestCard.getCardNo()));
        return cardResponseDto;
    }

    public String generatedMaskedCard(String cardNo) {
        int cardLength=cardNo.length();
        String maskedCard="";
        for(int i=0;i<cardLength-4;i++){
            maskedCard+='X';
        }
        maskedCard+=cardNo.substring(cardLength-4);
        return maskedCard;

    }

}
