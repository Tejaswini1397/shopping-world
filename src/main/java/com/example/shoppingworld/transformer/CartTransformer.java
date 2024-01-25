package com.example.shoppingworld.transformer;

import com.example.shoppingworld.Model.Cart;
import com.example.shoppingworld.Model.Item;
import com.example.shoppingworld.dto.ResponseDto.CardResponseDto;
import com.example.shoppingworld.dto.ResponseDto.CartResponseDto;
import com.example.shoppingworld.dto.ResponseDto.ItemResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){
        List<ItemResponseDto>responseDtoList=new ArrayList<>();
        for(Item item: cart.getList()){
            responseDtoList.add(ItemTransformer.ItemTOItemResponseDto(item));
        }
      return CartResponseDto.builder()
              .customerName(cart.getCustomer().getName())
              .cartTotal(cart.getCardTotal())
              .itemResponseDtoList(responseDtoList)
              .build();
    }
}
