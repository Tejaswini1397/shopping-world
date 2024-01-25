package com.example.shoppingworld.transformer;

import com.example.shoppingworld.Model.Customer;
import com.example.shoppingworld.dto.RequestDto.CustomerRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CustomerResponseDto;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .gender(customerRequestDto.getGender())
                .mobileNo(customerRequestDto.getMobileNo())
                .build();
    }
    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .gender(customer.getGender())
                .mobileNo(customer.getMobileNo())
                .build();
    }
}
