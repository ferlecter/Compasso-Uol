package com.compassouol.productms.repository;

import com.compassouol.productms.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> ,ProductCustomRepository{

}
