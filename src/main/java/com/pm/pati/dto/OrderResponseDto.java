package com.pm.pati.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.pm.pati.entity.OrderStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
