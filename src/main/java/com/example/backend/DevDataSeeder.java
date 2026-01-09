package com.example.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;

@Component
public class DevDataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DevDataSeeder(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setId(null);
            admin.setEmail("admin@local.com");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }

        if (productRepository.count() == 0) {
            Product p1 = new Product();
            p1.setId(null);
            p1.setName("Basic T-Shirt");
            p1.setPrice(12.99);

            Product p2 = new Product();
            p2.setId(null);
            p2.setName("Running Shoes");
            p2.setPrice(59.50);

            productRepository.save(p1);
            productRepository.save(p2);
        }
    }
}
