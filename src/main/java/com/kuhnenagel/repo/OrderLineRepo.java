package com.kuhnenagel.repo;

import com.kuhnenagel.model.Order;
import com.kuhnenagel.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface OrderLineRepo extends JpaRepository<OrderLine, Long> {;
    List<OrderLine> findByOrder(Order order);
}
