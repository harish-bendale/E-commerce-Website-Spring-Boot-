package com.owneravatar.ecommerce.Service;

import com.owneravatar.ecommerce.converter.ProductConverter;
import com.owneravatar.ecommerce.dto.ProductDTO;
import com.owneravatar.ecommerce.exception.ResourceNotFoundException;
import com.owneravatar.ecommerce.model.Product;
import com.owneravatar.ecommerce.repo.ProductRepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    private Cloudinary cloudinary;

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        try{
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),
                    ObjectUtils.asMap("folder", "Ecommerce_products"));

            String imageUrl = uploadResult.get("secure_url").toString();

            product.setImageUrl(imageUrl);
            System.out.println("Image URL " + imageUrl);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return productRepo.save(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(ProductConverter::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO findProductById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return ProductConverter.toDTO(product);
    }


    public Product updateProduct(int id, Product product, MultipartFile image) throws IOException {
        //product.setImageName(image.getOriginalFilename());
        //product.setImageType(image.getContentType());
        //product.setImageData(image.getBytes());

        return productRepo.save(product);
    }
}
