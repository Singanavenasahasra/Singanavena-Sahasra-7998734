package com.example.productservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication @EnableDiscoveryClient @RestController @RequestMapping("/products")
public class ProductServiceApplication {
    @Value("${shared-msg:Alternative Default Message}") private String centralMessage;
    public static void main(String[] args) { SpringApplication.run(ProductServiceApplication.class, args); }
    @GetMapping("/test-config") public String getConfigurationProperty() { return "Message from Config Server: " + centralMessage; }
}
