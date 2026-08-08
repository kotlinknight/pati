package com.pm.pati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.pati.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
