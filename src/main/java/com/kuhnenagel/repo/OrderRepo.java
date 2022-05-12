package com.kuhnenagel.repo;

import com.kuhnenagel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query("SELECT * " +
            "FROM order  " +
            "WHERE date_trunc(day, order.date) = date_trunc(day, :date) ")
    List<Order> findByDate(@Param("date") Instant date);
}
