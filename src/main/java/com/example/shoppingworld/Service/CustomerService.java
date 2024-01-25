package com.example.shoppingworld.Service;

import com.example.shoppingworld.Model.Cart;
import com.example.shoppingworld.Model.Customer;
import com.example.shoppingworld.Repository.CustomerRepository;
import com.example.shoppingworld.dto.RequestDto.CustomerRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CustomerResponseDto;
import com.example.shoppingworld.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto){
        Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart=new Cart();
        cart.setCardTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);
        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
