package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Orders;
import com.test_task.restaurant.repositories.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders createOrder(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Orders findOrderById(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found order with such id: " + id));
    }

    public List<Orders> findAllOrders() {
        return ordersRepository.findAll();
    }

    public void deleteOrderById(Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isEmpty()) throw new ResourceNotFoundException("Not found order with such id: " + id);
        ordersRepository.deleteById(id);
    }
}
