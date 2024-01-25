package com.example.shoppingworld.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)

public class ItemRequestDto {
    String customerEmail;
    int productId;
    int requiredQuantity;
}
