package com.hoandhh.backend.repositories;

import com.hoandhh.backend.models.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
        List<OrderDetail> findByOrderId(long orderId);
}
