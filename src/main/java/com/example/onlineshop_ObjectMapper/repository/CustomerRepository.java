package com.example.onlineshop_ObjectMapper.repository;

import com.example.onlineshop_ObjectMapper.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
