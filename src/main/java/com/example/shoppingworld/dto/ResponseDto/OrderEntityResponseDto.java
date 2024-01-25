package com.example.shoppingworld.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderEntityResponseDto {
    String orderId;
    Date orderDate;
    String cardUsed;
    int orderTotal;
    String customerName;
    List<ItemResponseDto> dtoList;
}
