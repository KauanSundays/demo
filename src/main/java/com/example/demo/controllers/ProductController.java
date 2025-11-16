package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

// import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ProductDto;
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

  @GetMapping("/{id}") // esse mapemaneto diz que quando tiver uma req get para /products seguida de um id deve ser executado
  public ResponseEntity getByID(@PathVariable(value = "id") Integer id ) { // pegando o valor do id que vem na requisição  e armazena na variavel java id do tipo integer
      Optional product = repository.findById(id);
      // optional quer dizer que pode ou nao encontrar o produto.
      //  
      return ResponseEntity.status(HttpStatus.OK).body(product.get());
  }

  @DeleteMapping("/{id}") // esse mapemaneto diz que quando tiver uma req delete para /products seguida de um id deve ser deletado
  public ResponseEntity deleteByID(@PathVariable(value = "id") Integer id ) { // pegando o valor do id que vem na requisição  e armazena na variavel java id do tipo integer
      Optional<Product> product = repository.findById(id);
      // optional quer dizer que pode ou nao encontrar o produto.
      repository.delete(product.get());
      return ResponseEntity.status(HttpStatus.OK).body("Apagado do banco");
  }
  
  @PostMapping
  public ResponseEntity save(@RequestBody ProductDto dto) {
    var product = new Product();
    BeanUtils.copyProperties(dto, product);
    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
  }

  
}
