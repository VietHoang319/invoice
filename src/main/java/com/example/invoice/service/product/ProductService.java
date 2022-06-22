package com.example.invoice.service.product;

import com.example.invoice.model.Product;
import com.example.invoice.service.GenaralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ProductService extends GenaralService<Product> {
    Page<Product> findAllByQuantity(Pageable pageable);
    Iterable<Product> findByName(String name);
    Iterable<Product> findAllOrderByPrice(boolean flag);
    Iterable<Object[]> reportByCreateTime(LocalDate dateFrom, LocalDate dateTo);
}
