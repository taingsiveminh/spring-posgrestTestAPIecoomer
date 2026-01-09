package com.example.backend.controller;

import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    // USER PLACE ORDER
    @PostMapping("/orders")
    public Order create(@RequestBody Order order) {
        order.setId(null);
        order.setStatus("PENDING");
        return repo.save(order);
    }

    // ADMIN VIEW ORDERS
    @GetMapping("/admin/orders")
    public List<Order> getAll() {
        return repo.findAll();
    }
}
