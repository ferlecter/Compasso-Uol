package com.compassouol.productms.service.imp;

import com.compassouol.productms.model.Product;
import com.compassouol.productms.repository.ProductCustomRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Product> getByDescription(String q, float maxValue, float minValue) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (q != null && !q.isEmpty()) {
            criteria.add(new Criteria().orOperator(                 
                    Criteria.where("descripton").is(q),Criteria.where("name").is(q)));
            
        }
        
       
        
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Product.class);
    }
  
}
