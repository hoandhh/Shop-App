package com.hoandhh.backend.services;

import java.util.List;

import com.hoandhh.backend.dtos.OrderDetailDTO;
import com.hoandhh.backend.exceptions.DataNotFoundException;
import com.hoandhh.backend.models.OrderDetail;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO newOrderDetail) throws Exception;

    OrderDetail getOrderDetail(Long id) throws DataNotFoundException;

    OrderDetail updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData)
            throws DataNotFoundException;

    void deleteById(Long id);

    List<OrderDetail> findByOrderId(Long orderId);
}
