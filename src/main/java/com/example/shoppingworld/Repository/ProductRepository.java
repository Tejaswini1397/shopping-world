package com.example.shoppingworld.Repository;

import com.example.shoppingworld.Enum.ProductCategory;
import com.example.shoppingworld.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select p from Product p where p.price>=:price and p.productCategory=:category")
    public List<Product> getProductByCategoryAndPriceGreaterThan(int price, ProductCategory category);


}
