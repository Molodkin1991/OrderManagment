package com.kuhnenagel.service;

import com.kuhnenagel.model.Customer;
import com.kuhnenagel.model.Order;
import com.kuhnenagel.model.OrderLine;
import com.kuhnenagel.model.Product;
import com.kuhnenagel.repo.OrderLineRepo;
import com.kuhnenagel.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderLineRepo orderLineRepo;
    private OrderRepo orderRepo;
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        orderLineRepo = mock(OrderLineRepo.class);
        orderRepo = mock(OrderRepo.class);
        orderService = new OrderService(orderLineRepo, orderRepo);
    }

    @Test
    void testGetOrderLineByDate() {
        Instant date = Instant.now();
        orderService.getOrdersByDate(date);
        verify(orderRepo, timeout(1)).findByDate(date);
    }

    @Test
    void testAddLineForOrderDate() {
        Instant date = Instant.now();
        orderService.addLineForOrder(new Customer(), new Product(), 2, new Order(date));
        verify(orderLineRepo, timeout(1)).save(new OrderLine(new Product(), new Customer(), 2, new Order(date)));
    }

    @Test
    void testCreateOrder() {
        when(orderRepo.save(any(Order.class))).then(AdditionalAnswers.returnsFirstArg());
        Order returnedOrder = orderService.createOrder();

        verify(orderRepo, times(1)).save(any(Order.class));
        assertNotNull(returnedOrder);
    }

    @Test
    void testCreateOrderWithLines() {
        OrderLine orderLine = new OrderLine();
        List<OrderLine> orderLines = List.of(orderLine);
        Instant date = Instant.now();
        Order order = new Order(date, orderLines);
        when(orderService.createOrder(orderLines)).thenReturn(order);
        Order returnedOrder = orderService.createOrder(orderLines);

        verify(orderRepo, times(1)).save(any(Order.class));
        assertNotNull(returnedOrder);
        assertEquals(orderLine, returnedOrder.getOrderLines().get(0));
    }
}