package com.example.shoppingworld.transformer;

import com.example.shoppingworld.Model.Seller;
import com.example.shoppingworld.dto.RequestDto.SellerRequestDto;
import com.example.shoppingworld.dto.ResponseDto.SellerResponseDto;

public class SellerTransformer {
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .panNo(sellerRequestDto.getPanNo())
                .emailId(sellerRequestDto.getEmailId())
                .build();
    }
    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .emailId(seller.getEmailId())
                .name(seller.getName())
                .build();
    }
}
