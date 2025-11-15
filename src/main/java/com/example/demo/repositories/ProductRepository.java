package com.example.demo.repositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Product;

@SpringBootApplication
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
