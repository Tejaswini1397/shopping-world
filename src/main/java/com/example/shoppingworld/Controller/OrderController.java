package com.example.shoppingworld.Controller;

import com.example.shoppingworld.Service.OrderService;
import com.example.shoppingworld.dto.RequestDto.OrderEntityRequestDto;
import com.example.shoppingworld.dto.ResponseDto.OrderEntityResponseDto;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/place_order")
    public ResponseEntity placeOrder(@RequestBody OrderEntityRequestDto orderEntityRequestDto){
        try{
            OrderEntityResponseDto orderEntityResponseDto=orderService.placeOrder(orderEntityRequestDto);
            return new ResponseEntity(orderEntityResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
