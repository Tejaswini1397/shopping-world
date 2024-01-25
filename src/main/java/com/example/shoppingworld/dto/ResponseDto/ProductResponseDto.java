package com.example.shoppingworld.dto.ResponseDto;

import com.example.shoppingworld.Enum.ProductCategory;
import com.example.shoppingworld.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String sellerEmail;
    String productName;
    int availableQuantity;
    int price;
    ProductCategory productCategory;
    ProductStatus productStatus;
}
