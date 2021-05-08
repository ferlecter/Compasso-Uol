package com.compassouol.productms.service.imp;

import com.compassouol.productms.model.Product;
import com.compassouol.productms.repository.ProductCustomRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
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
            final String regex = "(?i).*" + Pattern.quote(q) + ".*";
            criteria.add(new Criteria().orOperator(
                    Criteria.where("description").regex(regex),
                    Criteria.where("name").regex(regex)));
        }
        if (maxValue > 0) {
            criteria.add(Criteria.where("price").gte(maxValue));
        }
        
        if (minValue > 0) {
            criteria.add(Criteria.where("price").lte(minValue));
        }

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public Product saveProduct(Product product) throws Exception {

        if (product.getName().isEmpty() || product.getName() == null) {
            throw new Exception("Name é obrigatorio");
        }
        if (product.getDescription().isEmpty() || product.getDescription() == null) {
            throw new Exception("Description é obrigatorio");
        }

        if (product.getPrice() <= 0) {
            throw new Exception("Price deve ser maior que 0");
        }

        return this.mongoTemplate.save(product);
    }

}
