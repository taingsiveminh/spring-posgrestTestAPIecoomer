package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;

    private Long productId;
    private Integer qty;

    private String status;

    public Long getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQty() {
        return qty;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
