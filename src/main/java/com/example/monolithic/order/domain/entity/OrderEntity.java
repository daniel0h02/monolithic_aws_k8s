package com.example.monolithic.order.domain.entity;


import com.example.monolithic.common.domain.BaseTimeEntity;
import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.domain.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name="MONOLITHIC_ORDER_TBL")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseTimeEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product ;


    private Integer qty ; //수량
    
    @Enumerated(EnumType.STRING)
    @Builder.Default        //기본값을 세팅해줌, builder가 무시할 수 있어서(Emumerater) 그걸 방지라고 안정적이게 할 수 있음
    private OrderStatus orderStatus = OrderStatus.ORDERED ;

}
