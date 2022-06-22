package com.example.invoice.controller;

import com.example.invoice.model.OrderDetail;
import com.example.invoice.model.Orderr;
import com.example.invoice.model.Product;
import com.example.invoice.service.order.OrderService;
import com.example.invoice.service.order_detail.OrderDetailService;
import com.example.invoice.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders/{id}")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<OrderDetail> create(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        orderDetail.setOrderr(orderService.findById(id).get());
        orderDetailService.save(orderDetail);
        Product product = orderDetail.getProduct();
        product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
        productService.save(product);
        Orderr orderr = orderDetail.getOrderr();
        long total = orderr.getAmount() + product.getPrice() * orderDetail.getQuantity();
        orderr.setAmount(total);
        orderService.save(orderr);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }

    @DeleteMapping("/details/{idd}")
    public ResponseEntity<OrderDetail> delete(@PathVariable Long idd) {
        OrderDetail orderDetail = orderDetailService.findById(idd).get();
        orderDetailService.remove(idd);
        Product product = orderDetail.getProduct();
        product.setQuantity(product.getQuantity() + orderDetail.getQuantity());
        productService.save(product);
        Orderr orderr = orderDetail.getOrderr();
        long total = orderr.getAmount() - product.getPrice() * orderDetail.getQuantity();
        orderr.setAmount(total);
        orderService.save(orderr);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/details/{idd}")
    public ResponseEntity<OrderDetail> update(@PathVariable Long id, @PathVariable Long idd, @RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> oldOrderDetail = orderDetailService.findById(idd);
        if(!oldOrderDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setId(oldOrderDetail.get().getId());
        orderDetail.setOrderr(oldOrderDetail.get().getOrderr());

        Product oldProduct = oldOrderDetail.get().getProduct();
        oldProduct.setQuantity(oldProduct.getQuantity() + oldOrderDetail.get().getQuantity());
        long oldMoney = oldProduct.getPrice() * oldOrderDetail.get().getQuantity();
        productService.save(oldProduct);
        orderDetailService.save(orderDetail);
        OrderDetail newOrderDetail = orderDetailService.findById(orderDetail.getId()).get();
        Product newProduct = newOrderDetail.getProduct();
        newProduct.setQuantity(newProduct.getQuantity() - newOrderDetail.getQuantity());
        productService.save(newProduct);

        Orderr newOrder = newOrderDetail.getOrderr();
        newOrder.setAmount(newOrder.getAmount() - oldMoney + newOrderDetail.getQuantity() * newProduct.getPrice());
        orderService.save(newOrder);
        return new ResponseEntity<>(orderDetailService.findById(orderDetail.getId()).get(), HttpStatus.OK);
    }
}
