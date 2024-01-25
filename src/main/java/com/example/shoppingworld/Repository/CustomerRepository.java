package com.example.shoppingworld.Repository;

import com.example.shoppingworld.Model.Customer;
import com.example.shoppingworld.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByMobileNo(String mobileNo);
    public Customer findByEmailId(String emailId);
}
