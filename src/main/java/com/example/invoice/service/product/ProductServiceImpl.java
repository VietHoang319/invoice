package com.example.invoice.service.product;

import com.example.invoice.model.Product;
import com.example.invoice.repository.ProductRepository;
import com.example.invoice.repository.no_entity.ReportByCreateAt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByQuantity(Pageable pageable) {
        return productRepository.findAllByQuantity(pageable);
    }

    @Override
    public Iterable<Product> findByName(String name) {
        return productRepository.findAlByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAllOrderByPrice(boolean flag) {
        return productRepository.findAllOrderByPrice(flag);
    }

    @Override
    public Iterable<ReportByCreateAt> reportByCreateTime(LocalDate dateFrom, LocalDate dateTo) {
        return productRepository.reportByCreateTime(dateFrom, dateTo);
    }


}
