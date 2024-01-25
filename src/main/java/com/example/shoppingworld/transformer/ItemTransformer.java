package com.example.shoppingworld.transformer;

import com.example.shoppingworld.Model.Item;
import com.example.shoppingworld.dto.RequestDto.ItemRequestDto;
import com.example.shoppingworld.dto.ResponseDto.ItemResponseDto;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem(int requiredQuantity){
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }
    public static ItemResponseDto ItemTOItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .itemName(item.getProduct().getProductName())
                .itemPrice(item.getProduct().getPrice())
                .quantityAdded(item.getRequiredQuantity())
                .productCategory(item.getProduct().getProductCategory())
                .build();
    }
}
