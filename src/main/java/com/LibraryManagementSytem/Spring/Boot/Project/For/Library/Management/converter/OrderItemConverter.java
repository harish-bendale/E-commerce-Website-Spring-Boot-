package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.converter;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.OrderItemDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.OrderItem;

public class OrderItemConverter {
    public static OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(orderItem.getProduct().getId());
        dto.setProductName(orderItem.getProduct().getName());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getProduct().getPrice());

        return dto;
    }
}
