package com.example.onlineshop_ObjectMapper.controller;

import com.example.onlineshop_ObjectMapper.model.Order;
import com.example.onlineshop_ObjectMapper.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderController(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderById(@PathVariable("id") int id) {
        String s = objectMapper.writeValueAsString(orderService.getOrderById(id));
        return ResponseEntity.ok(s);
    }

    @SneakyThrows
    @PostMapping()
    public ResponseEntity<Order> addNewOrder(String order) {
        Order order1 = objectMapper.readValue(order, Order.class);
        orderService.addNewOrder(order1);
        return ResponseEntity.ok(order1);
    }
}
