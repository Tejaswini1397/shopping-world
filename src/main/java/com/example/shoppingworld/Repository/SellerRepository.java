package com.example.shoppingworld.Repository;

import com.example.shoppingworld.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    public Seller findByEmailId(String emailId);
}
