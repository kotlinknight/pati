package com.pm.pati.service.impl;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.pati.dto.OrderRequestDto;
import com.pm.pati.dto.OrderResponseDto;
import com.pm.pati.entity.Order;
import com.pm.pati.entity.OrderStatus;
import com.pm.pati.entity.Product;
import com.pm.pati.exception.InsufficientStockException;
import com.pm.pati.exception.ResourceNotFoundException;
import com.pm.pati.repository.OrderRepository;
import com.pm.pati.repository.ProductRepository;
import com.pm.pati.service.OrderService;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Product product = productRepository
                .findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + dto.getProductId()));

        if (product.getStockQuantity() < dto.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock for product id: " + dto.getProductId()
                    + ". Available: " + product.getStockQuantity() + ", requested: " + dto.getQuantity());
        }

        // Deduct stock
        product.setStockQuantity(product.getStockQuantity() - dto.getQuantity());
        productRepository.save(product);

        Order order = Order.builder()
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .status(OrderStatus.PLACED)
                .createdAt(LocalDateTime.now())
                .build();

        Order saved = orderRepository.save(order);
        return mapToResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return mapToResponseDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(this::mapToResponseDto);
    }

    private OrderResponseDto mapToResponseDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
