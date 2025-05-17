package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.controller;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.Service.OrderService;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.CreateOrderDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.OrderResponseDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody CreateOrderDTO dto) {
        try {
            Order order = orderService.placeOrder(dto);
            // Map order to response DTO
            OrderResponseDTO response = orderService.mapToOrderResponseDTO(order);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to place order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>> getOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/order/id/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        OrderResponseDTO dto = orderService.getOrderById(orderId);
        if(dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Failed to find order of id " + orderId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order/email/{emailId}")
    public ResponseEntity<?> getOrderByEmailId(@PathVariable String emailId) {
        List<OrderResponseDTO> dto = orderService.getOrderByEmail(emailId);
        if(!dto.isEmpty()) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No order from this user having email " + emailId, HttpStatus.NOT_FOUND);
        }
    }
}
