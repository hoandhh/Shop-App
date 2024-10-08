package com.hoandhh.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoandhh.backend.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long userId);
}
