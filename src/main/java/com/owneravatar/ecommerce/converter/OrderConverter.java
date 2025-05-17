package com.owneravatar.ecommerce.converter;

import com.owneravatar.ecommerce.dto.OrderItemDTO;
import com.owneravatar.ecommerce.dto.OrderResponseDTO;
import com.owneravatar.ecommerce.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderResponseDTO toDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setOrderTime(order.getOrderTime());
        dto.setStatus(order.getStatus());
        dto.setUser(UsersConverter.toDTO(order.getUser()));

        List<OrderItemDTO> items = order.getItems() != null ? order.getItems().stream()
                .map(OrderItemConverter::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();
        dto.setItems(items);
        return dto;
    }
}
