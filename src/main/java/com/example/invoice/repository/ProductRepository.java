package com.example.invoice.repository;

import com.example.invoice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from product where quantity > 300", nativeQuery = true)
    Page<Product> findAllByQuantity(Pageable pageable);
    Iterable<Product> findAlByNameContaining(String name);
    @Query(value = "CALL abc(:flag)", nativeQuery = true)
    Iterable<Product> findAllOrderByPrice(@Param("flag") boolean flag);

    @Query(value = "select p.id, p.name, sum(od.quantity)\n" +
            "from product p\n" +
            "join order_detail od on p.id = od.product_id\n" +
            "join invoice.orderr o on o.id = od.orderr_id\n" +
            "where o.create_at between :dateFrom and :dateTo\n" +
            "group by p.id;", nativeQuery = true)
    Iterable<Object[]> reportByCreateTime(@Param("dateFrom")LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}
