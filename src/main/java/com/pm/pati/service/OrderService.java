package com.pm.pati.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pm.pati.dto.OrderRequestDto;
import com.pm.pati.dto.OrderResponseDto;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto dto);

    OrderResponseDto getOrderById(Long id);

    Page<OrderResponseDto> getOrders(Pageable pageable);
}
