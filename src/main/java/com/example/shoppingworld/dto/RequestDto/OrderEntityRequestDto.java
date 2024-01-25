package com.example.shoppingworld.dto.RequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OrderEntityRequestDto {
    String customerEmail;
    int productId;
    String cardUsed;
    int cvv;
    int requiredQuantity;

}

