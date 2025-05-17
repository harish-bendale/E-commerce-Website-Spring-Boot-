package com.owneravatar.ecommerce.Service;

import com.owneravatar.ecommerce.converter.OrderConverter;
import com.owneravatar.ecommerce.dto.*;
import com.owneravatar.ecommerce.enums.OrderStatus;
import com.owneravatar.ecommerce.exception.ResourceNotFoundException;
import com.owneravatar.ecommerce.model.Order;
import com.owneravatar.ecommerce.model.OrderItem;
import com.owneravatar.ecommerce.model.Product;
import com.owneravatar.ecommerce.model.Users;
import com.owneravatar.ecommerce.repo.OrderRepository;
import com.owneravatar.ecommerce.repo.ProductRepo;
import com.owneravatar.ecommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepository orderRepo;

    public Order placeOrder(CreateOrderDTO dto) {
        Users user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED.toString());

        List<OrderItem> items = new ArrayList<>();
        for (OrderProductDTO prod : dto.getProducts()) {
            Product product = productRepo.findById(prod.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + prod.getProductId()));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(prod.getQuantity());
            items.add(item);
            item.setOrder(order);
        }

        order.setItems(items);
        return orderRepo.save(order);
    }

    public OrderResponseDTO mapToOrderResponseDTO(Order order) {
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setOrderId(order.getOrderId());
        responseDTO.setOrderTime(order.getOrderTime());
        responseDTO.setStatus(order.getStatus());

        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(order.getUser().getId());
        userDTO.setUsername(order.getUser().getUsername());
        userDTO.setEmail(order.getUser().getEmail());
        userDTO.setMobileNo(order.getUser().getMobileNo());
        responseDTO.setUser(userDTO);

        List<OrderItemDTO> itemDTOs = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getProduct().getPrice());
            itemDTOs.add(itemDTO);
        }

        responseDTO.setItems(itemDTOs);
        return responseDTO;
    }

    public List<OrderResponseDTO> getAllOrders() {

        return orderRepo.findAll().stream()
                .map(OrderConverter::toDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + orderId + "not found"));
        return OrderConverter.toDTO(order);
    }

    public List<OrderResponseDTO> getOrderByEmail(String email) {
        return  orderRepo.findOrdersByEmail(email)
                .stream()
                .map(OrderConverter::toDTO)
                .collect(Collectors.toList());
    }
}
