package com.example.shoppingworld.dto.RequestDto;

import com.example.shoppingworld.Enum.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    String sellerEmail;
    String productName;
    int price;
    int availableQuantity;
    ProductCategory productCategory;
}
