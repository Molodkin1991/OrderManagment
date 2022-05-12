package com.kuhnenagel.service;

import com.kuhnenagel.model.Customer;
import com.kuhnenagel.model.Order;
import com.kuhnenagel.model.OrderLine;
import com.kuhnenagel.model.Product;
import com.kuhnenagel.repo.OrderLineRepo;
import com.kuhnenagel.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    private final OrderLineRepo orderLineRepo;
    private final OrderRepo orderRepo;

    public OrderService(OrderLineRepo orderLineRepo, OrderRepo orderRepo) {
        this.orderLineRepo = orderLineRepo;
        this.orderRepo = orderRepo;
    }

    public List<Order> getOrdersByDate(Instant date) {
        return orderRepo.findByDate(date);
    }

    public OrderLine addLineForOrder(Customer customer,
                                     Product product,
                                     Integer quantity,
                                     Order order) {
        OrderLine orderLine = new OrderLine();
        orderLine.setCustomer(customer);
        orderLine.setProduct(product);
        orderLine.setQuantity(quantity);
        orderLine.setOrder(order);
        return orderLineRepo.save(orderLine);
    }

    public Order createOrder() {
        Order order = new Order();
        return orderRepo.save(order);
    }

    public Order createOrder(List<OrderLine> orderLines) {
        Order order = new Order();
        order.setOrderLines(orderLines);
        return orderRepo.save(order);
    }

}
