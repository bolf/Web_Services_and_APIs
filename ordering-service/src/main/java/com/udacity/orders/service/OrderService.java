package com.udacity.orders.service;

import com.udacity.orders.entity.Order;
import com.udacity.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order){
        order.setModifiedAt(LocalDateTime.now());
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order getByID(Long Id){
        return orderRepository.findById(Id).orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public void delete(Long id){
        orderRepository.delete(orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    }

}
