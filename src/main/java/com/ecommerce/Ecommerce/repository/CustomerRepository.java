package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByMobile(String mob);
    public Customer findByEmail(String email);
}
