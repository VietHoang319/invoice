package com.example.invoice.service.order;

import com.example.invoice.model.OrderDetail;
import com.example.invoice.model.Orderr;
import com.example.invoice.service.GenaralService;

public interface OrderService extends GenaralService<Orderr> {
    Iterable<OrderDetail> findDetail (Long id);
}
