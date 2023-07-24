package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.dto.request.ProductRequestDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.price >= :price and p.category = :category")
    List<Product> findByCategoryAndPrice(ProductCategory category, int price);
}
