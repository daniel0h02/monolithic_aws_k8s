package com.example.monolithic.order.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.monolithic.order.dao.OrderRepository;
import com.example.monolithic.order.domain.dto.OrderRequestDTO;
import com.example.monolithic.order.domain.dto.OrderResponseDTO;
import com.example.monolithic.order.domain.entity.OrderEntity;
import com.example.monolithic.product.dao.ProductRepositiry;
import com.example.monolithic.product.domain.dto.ProductResponseDTO;
import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.dao.UserRepository;
import com.example.monolithic.user.domain.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepositiry productRepositiry;

    public OrderResponseDTO orderCreate(OrderRequestDTO request){
        System.out.println(">>>> order service orderCreate");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(">>>> Auth getNAme : " + auth.getName());

        //MSA로 가면 밑에 user와 product 코드가 당연하지 않게 된다
        UserEntity user = userRepository.findById(auth.getName())
                                        .orElseThrow(() -> new RuntimeException("User Not Found!!"));

        ProductEntity product = productRepositiry.findById(request.getProductId())
                                                 .orElseThrow(() -> new RuntimeException("Product Not Found!!"));

        //재고 관리
        System.out.println(">>>> order service 재고관리!!!!!!(중요)");
        
        // 재고 먼저 파악
        // 재고가 있으면 굳이 동기 방식으로 안해도 된다 async
        Integer qty = request.getQty();
        if(product.getStockQty() < qty){
            throw new RuntimeException(">>>> 재고 부족");

        }else{
            product.updateStockQty(request.getQty());
        }

        OrderEntity order = OrderEntity.builder()
                .qty(qty)
                .product(product)
                .user(user)
                .build();

        return OrderResponseDTO.fromEntity(orderRepository.save(order));
    }
}
