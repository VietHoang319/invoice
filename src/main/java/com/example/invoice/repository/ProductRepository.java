package com.example.invoice.repository;

import com.example.invoice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from product where quantity > 300", nativeQuery = true)
    Page<Product> findAllByQuantity(Pageable pageable);
    Iterable<Product> findAlByNameContaining(String name);
    @Query(value = "CALL abc(:flag)", nativeQuery = true)
    Iterable<Product> findAllOrderByPrice(@Param("flag") boolean flag);
}
