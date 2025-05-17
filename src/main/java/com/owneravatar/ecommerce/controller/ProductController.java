package com.owneravatar.ecommerce.controller;

import com.owneravatar.ecommerce.Service.ProductService;
import com.owneravatar.ecommerce.dto.ProductDTO;
import com.owneravatar.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public  ResponseEntity<?> getProductById(@PathVariable Long prodId) {
        ProductDTO product = productService.findProductById(prodId);
        if(product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
        System.out.println("Received image: " + imageFile.getOriginalFilename());
        System.out.println("Received image: " + imageFile.getContentType());
        try{
            return new ResponseEntity<>(productService.addProduct(product, imageFile), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add Product " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart("imageFile") MultipartFile image) throws IOException {
        Product product1 = null;
        try{
            product1 = productService.updateProduct(id, product, image);
        }catch(IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }

        if(product1 != null) {
            return new ResponseEntity<>(product1, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
}
