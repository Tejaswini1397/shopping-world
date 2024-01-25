package com.example.shoppingworld.Controller;

import com.example.shoppingworld.Enum.ProductCategory;
import com.example.shoppingworld.Model.Product;
import com.example.shoppingworld.Service.ProductService;
import com.example.shoppingworld.dto.RequestDto.ProductRequestDto;
import com.example.shoppingworld.dto.ResponseDto.ProductResponseDto;
import com.example.shoppingworld.exception.SellerNotFoundException;
import com.example.shoppingworld.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add_Product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto responseDto= productService.addProduct(productRequestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_product_greater_than")
    public ResponseEntity getProductByCategoryAndPriceGreaterThan(@RequestParam("price") int price,
                                                               @RequestParam("category") ProductCategory category){
        List<ProductResponseDto> responseDtoList=productService.getProductByCategoryAndPriceGreaterThan(price,category);
        return new ResponseEntity(responseDtoList,HttpStatus.FOUND);
    }

}
