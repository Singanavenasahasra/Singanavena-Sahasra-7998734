package com.example.paymentservice;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication @RestController @RequestMapping("/payments")
public class PaymentApplication {
    private static final Logger log = LoggerFactory.getLogger(PaymentApplication.class);
    public static void main(String[] args) { SpringApplication.run(PaymentApplication.class, args); }
    @GetMapping("/process") 
    @CircuitBreaker(name = "thirdPartyPaymentBreaker", fallbackMethod = "alternativePaymentGateway")
    public String executeTransaction() {
        if (Math.random() > 0.3) { throw new RuntimeException("Third-party payment vendor API timeout error."); }
        return "Payment processed successfully via Primary Vendor API.";
    }
    public String alternativePaymentGateway(Exception e) {
        log.error("Circuit breaker caught exception: {}. Running backup gateway optimization route.", e.getMessage());
        return "Primary payment gateway is down. Processing your request through our offline backup service.";
    }
}
