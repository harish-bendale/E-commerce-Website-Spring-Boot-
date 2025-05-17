package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto;

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


