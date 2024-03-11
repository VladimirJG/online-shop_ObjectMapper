package com.example.onlineshop_ObjectMapper.service;

import com.example.onlineshop_ObjectMapper.model.Product;
import com.example.onlineshop_ObjectMapper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.ProviderNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(ProviderNotFoundException::new);
    }

    @Transactional
    public void saveNewProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Product upProduct, int id) {
        upProduct.setId(id);
        productRepository.save(upProduct);
    }

    @Transactional
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}