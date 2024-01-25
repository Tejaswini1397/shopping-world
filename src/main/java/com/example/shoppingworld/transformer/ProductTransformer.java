package com.example.shoppingworld.transformer;

import com.example.shoppingworld.Enum.ProductStatus;
import com.example.shoppingworld.Model.Product;
import com.example.shoppingworld.dto.RequestDto.ProductRequestDto;
import com.example.shoppingworld.dto.ResponseDto.ProductResponseDto;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productStatus(ProductStatus.AVAILABLE)
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .build();
    }
    public static ProductResponseDto ProductTOProductResponse(Product product){
        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .sellerEmail(product.getSeller().getEmailId())
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
