package com.example.shoppingworld.Service;

import com.example.shoppingworld.Model.*;
import com.example.shoppingworld.Repository.*;
import com.example.shoppingworld.dto.RequestDto.CheckOutCartRequestDto;
import com.example.shoppingworld.dto.RequestDto.ItemRequestDto;
import com.example.shoppingworld.dto.ResponseDto.CartResponseDto;
import com.example.shoppingworld.dto.ResponseDto.ItemResponseDto;
import com.example.shoppingworld.dto.ResponseDto.OrderEntityResponseDto;
import com.example.shoppingworld.exception.CustomerNotFoundException;
import com.example.shoppingworld.exception.EmptyCardException;
import com.example.shoppingworld.exception.InvalidCardException;
import com.example.shoppingworld.transformer.CartTransformer;
import com.example.shoppingworld.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;


@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderEntityRepository orderEntityRepository;
    @Autowired
    CardRepository cardRepository;
    public CartResponseDto addCart(ItemRequestDto itemRequestDto, Item item){
        Customer customer=customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product=productRepository.findById(itemRequestDto.getProductId()).get();
        Cart cart=customer.getCart();
        cart.setCardTotal(cart.getCardTotal()+itemRequestDto.getRequiredQuantity()* product.getPrice());
        item.setCart(cart);
        item.setProduct(product);

        Item savedItem=itemRepository.save(item);

        cart.getList().add(savedItem);
        product.getItems().add(savedItem);
        Cart savedCart=cartRepository.save(cart);
        productRepository.save(product);
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    public OrderEntityResponseDto checkCart(CheckOutCartRequestDto checkOutCartRequestDto) {
        Customer customer=customerRepository.findByEmailId(checkOutCartRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't Exist");
        }
        Card card=cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        Date currentDate = new Date();
        if(card==null || card.getCvv()!=checkOutCartRequestDto.getCvv() || currentDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid Card");
        }
        Cart cart=customer.getCart();
        if(cart.getList().size()==0){
            throw new EmptyCardException("Sorry! The Card is Empty.");
        }
        OrderEntity orderEntity=orderService.placeOrder(cart,card);
        resetCart(cart);

        OrderEntity savedOrder=orderEntityRepository.save(orderEntity);
        //prepare Responsedto
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }
    public void resetCart(Cart cart){
        cart.setCardTotal(0);
        for(Item item:cart.getList()){
            item.setCart(null);
        }
        cart.setList(new ArrayList<>());
    }
}
