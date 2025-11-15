package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.repositories.ProductRepository;

@RestController
// mostrando para o JPA que isso é um classe de contreoller
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductRepository repository;

  @GetMapping
  // Estou mostrando que é uma requisição get
  public ResponseEntity getAll() {
    List<Product> listProducts = repository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(listProducts);
  }
}
