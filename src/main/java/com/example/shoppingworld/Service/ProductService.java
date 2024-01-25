package com.example.shoppingworld.Service;

import com.example.shoppingworld.Enum.ProductCategory;
import com.example.shoppingworld.Model.Product;
import com.example.shoppingworld.Model.Seller;
import com.example.shoppingworld.Repository.CustomerRepository;
import com.example.shoppingworld.Repository.ProductRepository;
import com.example.shoppingworld.Repository.SellerRepository;
import com.example.shoppingworld.dto.RequestDto.ProductRequestDto;
import com.example.shoppingworld.dto.ResponseDto.ProductResponseDto;
import com.example.shoppingworld.exception.SellerNotFoundException;
import com.example.shoppingworld.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Seller seller=sellerRepository.findByEmailId(productRequestDto.getSellerEmail());
        if(seller==null){
            throw new SellerNotFoundException("Seller Doesn't Exist");
        }
        //dto to entity
        Product product= ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);
        Seller savedSeller=sellerRepository.save(seller);
        List<Product> productList=savedSeller.getProducts();
        Product latestproduct=productList.get(productList.size()-1);
        return ProductTransformer.ProductTOProductResponse(latestproduct);
    }

    public List<ProductResponseDto> getProductByCategoryAndPriceGreaterThan(int price, ProductCategory category) {
        List<Product>productList=productRepository.getProductByCategoryAndPriceGreaterThan(price,category);
        List<ProductResponseDto>responseDtoList=new ArrayList<>();
        for(Product product:productList){
            responseDtoList.add(ProductTransformer.ProductTOProductResponse(product));
        }
        return responseDtoList;
    }
}
