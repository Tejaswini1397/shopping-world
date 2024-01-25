package com.example.shoppingworld.Service;

import com.example.shoppingworld.Model.Customer;
import com.example.shoppingworld.Model.Item;
import com.example.shoppingworld.Model.Product;
import com.example.shoppingworld.Repository.CustomerRepository;
import com.example.shoppingworld.Repository.ProductRepository;
import com.example.shoppingworld.dto.RequestDto.ItemRequestDto;
import com.example.shoppingworld.dto.ResponseDto.ItemResponseDto;
import com.example.shoppingworld.exception.CustomerNotFoundException;
import com.example.shoppingworld.exception.InsufficientQuantityException;
import com.example.shoppingworld.exception.ProductNotFoundException;
import com.example.shoppingworld.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    public Item createItem(ItemRequestDto itemRequestDto){
        Customer customer=customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer Doesn't Exits");
        }
        Optional<Product>optionalProduct=productRepository.findById(itemRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product Doesn't Exist");
        }
        Product product=optionalProduct.get();
        //check quantity is available or not
        if(product.getAvailableQuantity()<itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry Required Quantity Not Available");
        }
        Item item= ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
