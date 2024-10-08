package com.hoandhh.backend.services;

import java.util.List;

import com.hoandhh.backend.dtos.OrderDTO;
import com.hoandhh.backend.exceptions.DataNotFoundException;
import com.hoandhh.backend.models.Order;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO) throws Exception;

    Order getOrder(long id);

    Order updateOrder(long id, OrderDTO orderDTO) throws DataNotFoundException;

    void deleteOrder(long id);

    List<Order> findByUserId(long userId);
}
