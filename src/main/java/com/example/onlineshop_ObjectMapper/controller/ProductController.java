package com.example.onlineshop_ObjectMapper.controller;

import com.example.onlineshop_ObjectMapper.model.Product;
import com.example.onlineshop_ObjectMapper.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
public class ProductController {
    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @Autowired
    public ProductController(ObjectMapper objectMapper, ProductService productService) {
        this.objectMapper = objectMapper;
        this.productService = productService;
    }

    @GetMapping()
    public List<String> getAllProducts() {
        return productService.getAllProducts().stream().map(p -> {
            try {
                return objectMapper.writeValueAsString(p);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") int id) {
        return objectMapper.writeValueAsString(productService.getProductById(id));
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<String> createNewProduct(@RequestBody @Valid String product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(product);
        }
        Product product1 = objectMapper.readValue(product, Product.class);
        productService.saveNewProduct(product1);
        return ResponseEntity.ok(objectMapper.writeValueAsString(product1));
    }

    @SneakyThrows
    @PatchMapping("/{id}")
    public HttpStatus updateProduct(@PathVariable("id") int id, String updateProduct) {
        Product product = objectMapper.readValue(updateProduct, Product.class);
        productService.updateProduct(product, id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}