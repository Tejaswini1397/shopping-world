package com.example.shoppingworld.Controller;

import com.example.shoppingworld.Service.CustomerService;
import com.example.shoppingworld.dto.RequestDto.CustomerRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CustomerResponseDto;
import com.example.shoppingworld.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add_customer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto response= customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
