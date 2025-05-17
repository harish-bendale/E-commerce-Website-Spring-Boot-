package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.converter;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.ProductDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.ReviewDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {
    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setBrand(product.getBrand());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setProductAvailable(product.isProductAvailable());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setImageUrl(product.getImageUrl());

        List<ReviewDTO> reviewDTOs = product.getReviews() != null
                ? product.getReviews().stream()
                .map(ReviewConverter::toDTO)
                .collect(Collectors.toList())
                : new ArrayList<>();

        dto.setReviews(reviewDTOs);


        return dto;
    }
}

