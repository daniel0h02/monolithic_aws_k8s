package com.example.monolithic.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.monolithic.product.domain.entity.ProductEntity;

@Repository
public interface ProductRepositiry extends JpaRepository<ProductEntity, Long>{
    
}
