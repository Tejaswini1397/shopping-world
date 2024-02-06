package com.example.shoppingworld.Repository;

import com.example.shoppingworld.Model.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {

    OrderEntity findByOrderId(String orderId);
}
