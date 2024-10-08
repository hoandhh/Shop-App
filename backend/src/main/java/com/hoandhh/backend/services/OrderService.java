package com.hoandhh.backend.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hoandhh.backend.dtos.OrderDTO;
import com.hoandhh.backend.exceptions.DataNotFoundException;
import com.hoandhh.backend.models.Order;
import com.hoandhh.backend.models.OrderStatus;
import com.hoandhh.backend.models.User;
import com.hoandhh.backend.repositories.OrderRepository;
import com.hoandhh.backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Order createOrder(OrderDTO orderDTO) throws Exception {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + orderDTO.getUserId()));
        // Convert OrderDTO -> Order by Model Mapper
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        // Cập nhật các trường của đơn hàng từ orderDTO
        Order order = new Order();
        modelMapper.map(orderDTO, order);
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        // Kiểm tra shipping date >= ngày hôm nay
        LocalDate shippingDate = orderDTO.getShippingDate() != null ? orderDTO.getShippingDate() : LocalDate.now();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Date must be at least today");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order getOrder(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(long id, OrderDTO orderDTO) throws DataNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: " + id));
        User existingUser = userRepository.findById(
                orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + id));
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        modelMapper.map(orderDTO, order);
        order.setUser(existingUser);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        // Soft delete -> set active = false
        if (order != null) {
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findByUserId(long userId) {
        return orderRepository.findByUserId(userId);
    }
}
