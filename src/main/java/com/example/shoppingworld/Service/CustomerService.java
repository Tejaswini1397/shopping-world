package com.example.shoppingworld.Service;

import com.example.shoppingworld.Model.Cart;
import com.example.shoppingworld.Model.Customer;
import com.example.shoppingworld.Repository.CustomerRepository;
import com.example.shoppingworld.dto.RequestDto.CustomerRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CustomerResponseDto;
import com.example.shoppingworld.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public CustomerResponseDto putCustomer(Integer customerId,CustomerRequestDto customerRequestDto){
        Optional<Customer>optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            Customer existingCustomer=optionalCustomer.get();
            //update existing customer with new data from the DTO
            existingCustomer.setName(customerRequestDto.getName());
            existingCustomer.setEmailId(customerRequestDto.getEmailId());

            //save the update customer
            Customer customer=customerRepository.save(existingCustomer);
            return CustomerTransformer.CustomerToCustomerResponseDto(customer);
        }else {
//            handle case where customer is not found
            return null;
        }
    }
    public CustomerResponseDto getCustomer(Integer customerId){
        Optional<Customer>optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            Customer customer=optionalCustomer.get();
            return CustomerTransformer.CustomerToCustomerResponseDto(customer);
        }else {
            return null;
        }
    }
}
