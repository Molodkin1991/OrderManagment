package com.kuhnenagel.controller;

import com.kuhnenagel.model.Customer;
import com.kuhnenagel.model.Order;
import com.kuhnenagel.model.OrderLine;
import com.kuhnenagel.model.Product;
import com.kuhnenagel.service.CustomerService;
import com.kuhnenagel.service.OrderService;
import com.kuhnenagel.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("order/v1/")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @PostMapping(value = "/create/customer")
    public ResponseEntity<Customer> createCustomerRecord(@RequestParam Integer code,
                                                         @RequestParam String name,
                                                         @RequestParam String email,
                                                         @RequestParam Integer phone) {

        try {
            return ResponseEntity.ok(customerService.createCustomer(code, name, email, phone));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/create/product")
    public ResponseEntity<Product> createProductRecord(@RequestParam String nameOfProduct,
                                                       @RequestParam Integer skuCode,
                                                       @RequestParam Integer unitPrice) {

        try {
            return ResponseEntity.ok(productService.createProduct(nameOfProduct, skuCode, unitPrice));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/create/order")
    public ResponseEntity<Order> createOrderRecord() {
        try {
            return ResponseEntity.ok(orderService.createOrder());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/create/order")
    public ResponseEntity<Order> createOrderRecord(@RequestBody List<OrderLine> orderLine) {
        try {
            return ResponseEntity.ok(orderService.createOrder(orderLine));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/create/order")
    public ResponseEntity<List<Order>> getOrdersByDate(@RequestParam Instant date) {
        try {
            return ResponseEntity.ok(orderService.getOrdersByDate(date));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
