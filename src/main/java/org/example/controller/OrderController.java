package org.example.controller;

import org.example.entities.OrderEntity;
import org.example.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
		OrderEntity createdOrder = orderService.createOrder(order);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}
}
