package com.example.shoppingworld.dto.ResponseDto;

import com.example.shoppingworld.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String itemName;
    int itemPrice;
    int quantityAdded;
    ProductCategory productCategory;
}
