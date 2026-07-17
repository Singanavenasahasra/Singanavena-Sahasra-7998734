package com.example.orderservice;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}

class UserDTO {
    private Long id;
    private String name;
    private String email;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

@FeignClient(name = "user-client", url = "http://localhost:8081/users")
interface UserClient {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}

@Entity
@Table(name = "orders")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productDetails;
    private Long userId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductDetails() { return productDetails; }
    public void setProductDetails(String productDetails) { this.productDetails = productDetails; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

@Repository
interface OrderRepository extends JpaRepository<Order, Long> {}

class OrderResponse {
    private Order order;
    private UserDTO user;
    public OrderResponse(Order order, UserDTO user) { this.order = order; this.user = user; }
    public Order getOrder() { return order; }
    public UserDTO getUser() { return user; }
}

@RestController
@RequestMapping("/orders")
class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserClient userClient;

    @PostMapping
    @SuppressWarnings("null")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    @SuppressWarnings("null")
    public OrderResponse getOrderWithUser(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Order ID " + id + " was not found."));
        UserDTO user = userClient.getUserById(order.getUserId());
        return new OrderResponse(order, user);
    }
}