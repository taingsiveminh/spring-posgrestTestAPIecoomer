package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;

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
        // If user is authenticated via JWT, trust the JWT email over client-provided email.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof String email) {
            order.setCustomerEmail(email);
        }
        order.setStatus("PENDING");
        return repo.save(order);
    }

    // USER VIEW OWN ORDERS (requires JWT)
    @GetMapping("/orders/me")
    public List<Order> myOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || !(auth.getPrincipal() instanceof String email) || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return repo.findByCustomerEmailOrderByIdDesc(email);
    }

    // ADMIN VIEW ORDERS
    @GetMapping("/admin/orders")
    public List<Order> getAll() {
	    return repo.findAllByOrderByIdDesc();
    }
}
