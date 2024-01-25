package com.example.shoppingworld.Service;

import com.example.shoppingworld.Model.Seller;
import com.example.shoppingworld.Repository.SellerRepository;
import com.example.shoppingworld.dto.RequestDto.SellerRequestDto;
import com.example.shoppingworld.dto.ResponseDto.SellerResponseDto;
import com.example.shoppingworld.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){
        Seller seller= SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller=sellerRepository.save(seller);
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);

    }
}
