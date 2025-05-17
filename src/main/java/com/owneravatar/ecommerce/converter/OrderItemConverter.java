package com.owneravatar.ecommerce.converter;

import com.owneravatar.ecommerce.dto.OrderItemDTO;
import com.owneravatar.ecommerce.model.OrderItem;

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
