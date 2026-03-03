package com.example.monolithic.product.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.monolithic.product.dao.ProductRepositiry;
import com.example.monolithic.product.domain.dto.ProductRequestDTO;
import com.example.monolithic.product.domain.dto.ProductResponseDTO;
import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.dao.UserRepository;
import com.example.monolithic.user.domain.entity.UserEntity;

import lombok.RequiredArgsConstructor;



@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepositiry productRepositiry;
    private final UserRepository    userRepository;


    public ProductResponseDTO productCreate(ProductRequestDTO request){
        System.out.println(">>>> product service productCreate");
        
        //user_id가 
        //보안적으로 안정적인 코드
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(">>>> Auth getNAme : " + auth.getName());
        UserEntity user = userRepository.findById(auth.getName())
                                        .orElseThrow(() -> new RuntimeException("Not Found!!"));

        ProductEntity product = request.toEntity(user);
        return ProductResponseDTO.fromEntity(productRepositiry.save(product));

    }
}
