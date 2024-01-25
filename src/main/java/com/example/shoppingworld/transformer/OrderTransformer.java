package com.example.shoppingworld.transformer;

import com.example.shoppingworld.Model.Item;
import com.example.shoppingworld.Model.OrderEntity;
import com.example.shoppingworld.dto.ResponseDto.ItemResponseDto;
import com.example.shoppingworld.dto.ResponseDto.OrderEntityResponseDto;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {
    public static OrderEntityResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){
        List<ItemResponseDto>itemResponseDtoList=new ArrayList<>();
        for(Item item:orderEntity.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemTOItemResponseDto(item));
        }
        return OrderEntityResponseDto.builder()
                .orderId(orderEntity.getOrderId())
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .orderTotal(orderEntity.getOrderTotal())
                .customerName(orderEntity.getCustomer().getName())
                .dtoList(itemResponseDtoList)
                .build();
    }
}
