// ...existing code...
package org.example.controller;

import org.example.entities.OrderEntity;
import org.example.services.OrderService;
import org.example.dto.OrderDTO;
import org.example.util.MapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	// Obtener pedidos por producto (para proveedor/restaurante)
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<OrderDTO>> getOrdersByProduct(@PathVariable Long productId) {
		logger.info("[GET] /orders/product/{} - Consultando pedidos para producto {}", productId, productId);
		List<OrderEntity> orders = orderService.getOrdersByProduct(productId);
		logger.info("Pedidos encontrados: {}", orders.size());
		List<OrderDTO> dtos = orders.stream().map(MapperUtil::toOrderDTO).toList();
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	// Obtener todos los pedidos
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderEntity> orders = orderService.getAllOrders();
		List<OrderDTO> dtos = orders.stream().map(MapperUtil::toOrderDTO).toList();
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) {
		List<OrderEntity> orders = orderService.getOrdersByUser(userId);
		List<OrderDTO> dtos = orders.stream().map(MapperUtil::toOrderDTO).toList();
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	       @PostMapping
	       public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
		       logger.info("[POST] /orders - Creando pedido: userId={}, productId={}, modalidad={}",
				       orderDTO.getUser() != null ? orderDTO.getUser().getId() : null,
				       orderDTO.getProduct() != null ? orderDTO.getProduct().getId() : null,
				       orderDTO.getModalidad()
		       );
		       try {
			       OrderEntity order = MapperUtil.toOrderEntity(orderDTO);
			       OrderEntity createdOrder = orderService.createOrder(order);
			       logger.info("Pedido creado con id {}", createdOrder.getId());
			       OrderDTO createdOrderDTO = MapperUtil.toOrderDTO(createdOrder);
			       return new ResponseEntity<>(createdOrderDTO, HttpStatus.CREATED);
		       } catch (RuntimeException ex) {
			       logger.error("Error al crear pedido: {}", ex.getMessage());
			       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		       } catch (Exception ex) {
			       logger.error("Error inesperado al crear pedido", ex);
			       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear pedido");
		       }
	       }
}
