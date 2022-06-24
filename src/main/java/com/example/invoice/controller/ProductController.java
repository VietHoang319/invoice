package com.example.invoice.controller;

import com.example.invoice.model.Product;
import com.example.invoice.repository.no_entity.ReportByCreateAt;
import com.example.invoice.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PageableDefault(value = 30) Pageable pageable) {
        return new ResponseEntity<>(productService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if(!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(product1.get().getId());
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/q300")
    public ResponseEntity<Page<Product>> findAllByQuantity(@PageableDefault(value = 4) Pageable pageable) {
        return  new ResponseEntity<>(productService.findAllByQuantity(pageable), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Product>> findByName(@RequestParam String name) {
        return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/orderBy")
    public ResponseEntity<Iterable<Product>> findAllOrderByPrice(@RequestParam boolean flag) {
        return new ResponseEntity<>(productService.findAllOrderByPrice(flag), HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<Iterable<ReportByCreateAt>> reportByCreateTime(@RequestParam String dateFrom, @RequestParam String dateTo) {
        return new ResponseEntity<>(productService.reportByCreateTime(LocalDate.parse(dateFrom), LocalDate.parse(dateTo)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
}
