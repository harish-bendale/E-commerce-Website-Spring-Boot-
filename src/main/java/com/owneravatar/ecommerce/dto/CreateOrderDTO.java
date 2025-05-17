package com.owneravatar.ecommerce.dto;

import java.util.List;

public class CreateOrderDTO {
    private Long userId;
    private List<OrderProductDTO> products;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }
}


