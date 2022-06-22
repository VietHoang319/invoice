package com.example.invoice.repository;

import com.example.invoice.model.OrderDetail;
import com.example.invoice.model.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orderr, Long> {
    @Query(value = "from OrderDetail where orderr.id = :id")
    Iterable<OrderDetail> findDetailByOrderId(@Param("id") Long id);
}
