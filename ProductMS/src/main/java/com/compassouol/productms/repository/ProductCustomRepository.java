package com.compassouol.productms.repository;

import com.compassouol.productms.model.Product;
import java.util.List;


public interface ProductCustomRepository {
    public List<Product> getByDescription(String q, float maxValue, float minValue);
    public Product saveProduct(Product product) throws Exception;
    
}
