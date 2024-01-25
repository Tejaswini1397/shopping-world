package com.example.shoppingworld.Service;

import com.example.shoppingworld.Enum.ProductStatus;
import com.example.shoppingworld.Model.*;
import com.example.shoppingworld.Repository.*;
import com.example.shoppingworld.dto.RequestDto.CardRequestDto;
import com.example.shoppingworld.dto.RequestDto.OrderEntityRequestDto;
import com.example.shoppingworld.dto.ResponseDto.OrderEntityResponseDto;
import com.example.shoppingworld.exception.CustomerNotFoundException;
import com.example.shoppingworld.exception.InsufficientQuantityException;
import com.example.shoppingworld.exception.InvalidCardException;
import com.example.shoppingworld.exception.ProductNotFoundException;
import com.example.shoppingworld.transformer.ItemTransformer;
import com.example.shoppingworld.transformer.OrderTransformer;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderEntityRepository orderEntityRepository;
    @Autowired
    CardService cardService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    JavaMailSender javaMailSender;
    public OrderEntityResponseDto placeOrder(OrderEntityRequestDto orderEntityRequestDto){
        Customer customer=customerRepository.findByEmailId(orderEntityRequestDto.getCustomerEmail());
        customer.getId();
        if(customer==null){
            throw new CustomerNotFoundException("Customer Doesn't Exist");
        }
        Optional<Product>optionalProduct=productRepository.findById(orderEntityRequestDto.getProductId());
        if(optionalProduct==null){
            throw new ProductNotFoundException("Product doesn't Exist");
        }
        Card card=cardRepository.findByCardNo(orderEntityRequestDto.getCardUsed());
        Date todayDate=new Date();

        if(card==null || card.getCvv()!=orderEntityRequestDto.getCvv() || todayDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid card");
        }
        Product product=optionalProduct.get();
        if(product.getAvailableQuantity()<orderEntityRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Insufficient Quantity Available");
        }
        int newQuantity=product.getAvailableQuantity()-orderEntityRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        //prepare order Entity
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generatedMaskedCard(orderEntityRequestDto.getCardUsed()));
        orderEntity.setOrderTotal(orderEntityRequestDto.getRequiredQuantity()*product.getPrice());

        Item item= ItemTransformer.ItemRequestDtoToItem(orderEntityRequestDto.getRequiredQuantity());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);


        orderEntity.setCustomer(customer);
        orderEntity.getItems().add(item);

        OrderEntity savedOrder=orderEntityRepository.save(orderEntity);

        product.getItems().add(savedOrder.getItems().get(0));
        customer.getOrderEntities().add(savedOrder);
        //send Email
        sendEmail(orderEntity);

        //productRepository.save(product);
        //customerRepository.save(customer);
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }
    public OrderEntity placeOrder(Cart cart, Card card){
        OrderEntity order=new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(cardService.generatedMaskedCard(card.getCardNo()));

        int orderTotal=0;
        for(Item item:cart.getList()){
            Product product=item.getProduct();
            if(product.getAvailableQuantity()<item.getRequiredQuantity()){
                throw  new InsufficientQuantityException("Sorry! Insufficient quatity available for" + product.getProductName());
            }
            int newQuantity=product.getAvailableQuantity()-item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
             orderTotal+=product.getPrice()*item.getRequiredQuantity();
            item.setOrderEntity(order);
        }
        order.setOrderTotal(orderTotal);
        order.setItems(cart.getList());
        order.setCustomer(card.getCustomer());
        return order;
    }
    public void sendEmail(OrderEntity order){
        String text="Congratulations! Your order has been successfully placed. " +
                "Thank you for choosing our services." +
                " We will process your order and deliver it to you as soon as possible." +"\n"+
                " Following are the details: '\n'" +
                "Customer Name= "+order.getCustomer().getName()+"\n"+
                "Order Id= "+order.getOrderId()+"\n" +
                "Order Total= "+order.getOrderTotal()+"\n"+
                "Order Date= "+order.getOrderDate()+"\n"+
                "Mobile No= "+ order.getCustomer().getMobileNo()+"\n"+"\n"+
                "Thank you for shopping with us! If you have any questions or need assistance, feel free to contact our customer support. " +
                "Happy shopping!"+"\n"+
                "Contact us= "+ "1233-589-749";
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(order.getCustomer().getEmailId());
        simpleMailMessage.setFrom("nitakhairnar1397@gmail.com");
        simpleMailMessage.setSubject("Order Placed");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

}
