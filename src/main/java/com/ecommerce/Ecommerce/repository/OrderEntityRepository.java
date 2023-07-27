package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {
    public OrderEntity findByOrderId(String orderId);


}
