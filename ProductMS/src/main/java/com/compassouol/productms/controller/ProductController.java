package com.compassouol.productms.controller;

import com.compassouol.productms.model.Product;
import com.compassouol.productms.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllTutorials() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

   
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> Search(@RequestParam(value = "min_price", required = false) float minValue,
            @RequestParam(value = "max_price", required = false) float maxValue,
            @RequestParam(value = "q", required = false) String q) {
        List<Product> products = (List<Product>) productRepository.getByDescription(q,maxValue,minValue);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> save(@RequestBody Product pr) {

        if (pr.getPrice() <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Product product = this.productRepository.save(pr);

        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateTutorial(@PathVariable("id") String id, @RequestBody Product product) {

        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            Product pr = productData.get();
            pr.setDescription(product.getDescription());
            pr.setName(product.getName());
            pr.setPrice(product.getPrice());

            return new ResponseEntity<>(this.productRepository.save(pr), HttpStatus.OK);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
