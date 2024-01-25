package com.example.shoppingworld.Controller;

import com.example.shoppingworld.Model.Item;
import com.example.shoppingworld.Service.CartService;
import com.example.shoppingworld.Service.ItemService;
import com.example.shoppingworld.dto.RequestDto.CheckOutCartRequestDto;
import com.example.shoppingworld.dto.RequestDto.ItemRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CartResponseDto;
import com.example.shoppingworld.dto.ResponseDto.OrderEntityResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ItemService itemService;
    @PostMapping("/add_cart")
    public ResponseEntity addCart(@RequestBody ItemRequestDto itemRequestDto){
        try{
            Item item=itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto=cartService.addCart(itemRequestDto,item);
            return new ResponseEntity<>(cartResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/check_out")
    public ResponseEntity checkOut(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto){
        try {
            OrderEntityResponseDto orderEntityResponseDto=cartService.checkCart(checkOutCartRequestDto);
            return new ResponseEntity(orderEntityResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
