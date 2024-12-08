package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.Orders;
import com.test_task.restaurant.models.Orders.Status;
import com.test_task.restaurant.services.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = ordersService.findOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping()
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping()
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders createdOrder = ordersService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders ordersInfo) {
        Orders order = ordersService.findOrderById(id);
        if (ordersInfo.getClient() != null) {
            order.setClient(ordersInfo.getClient());
        }
        if (ordersInfo.getStatus() != null) {
            order.setStatus(ordersInfo.getStatus());
        }
        if (ordersInfo.getEmployees() != null) {
            order.setEmployees(ordersInfo.getEmployees());
        }
        Orders updatedOrder = ordersService.createOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable Long id, @RequestParam Status status) {
        Orders order = ordersService.findOrderById(id);
        order.setStatus(status);
        Orders updatedOrder = ordersService.createOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
