package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.converter;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.OrderItemDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.OrderResponseDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Order;

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
