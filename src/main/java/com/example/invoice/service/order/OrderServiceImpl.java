package com.example.invoice.service.order;

import com.example.invoice.model.OrderDetail;
import com.example.invoice.model.Orderr;
import com.example.invoice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Page<Orderr> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Orderr> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Orderr orderr) {
        orderRepository.save(orderr);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<OrderDetail> findDetail(Long id) {
        return orderRepository.findDetailByOrderId(id);
    }
}
