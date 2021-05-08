package com.compassouol.productms.controller;

import com.compassouol.productms.model.Product;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    ProductController productController;

    public ProductControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllProducts() {
        System.out.println("getAllProducts");

        ResponseEntity<List<Product>> result = productController.getAllProducts();
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    public void testGetProductById() {
        ResponseEntity<?> result = null;
        result = productController.getProductById("6096d005b114a1167b11f5db");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        
        result = productController.getProductById("6096c2582b5d843b03b66");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testSave() {
        System.out.println("save");
        Product pr = null;
        ResponseEntity<?> result = null;
        pr = new Product();
        pr.setDescription("teste");
        pr.setName("compasso");
        pr.setPrice(-1F);
        result = productController.save(pr);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

        pr = new Product();
        pr.setDescription("teste");
        pr.setName("compasso");
        pr.setPrice(1.99F);
        result = productController.save(pr);
        assertNotEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

    }

    @Test
    public void testUpdateProduct() {
        Product pr = null;
        ResponseEntity<?> result = null;
        pr = new Product();
        pr.setDescription("teste update");
        pr.setName("compasso");
        pr.setPrice(-1F);
        result = productController.updateProduct("6096d005b114a1167b11f5db", pr);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

        pr = new Product();
        pr.setDescription("teste");
        pr.setName("compasso");
        pr.setPrice(7F);
        result = result = productController.updateProduct("6096d005b114a1167b11f5db", pr);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        pr = new Product();
        pr.setDescription("teste");
        pr.setName("compasso");
        pr.setPrice(7F);
        result = result = productController.updateProduct("a", pr);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

    @Test
    public void testDeleteProduct() {
        ResponseEntity<?> result = null;

        result = productController.deleteProduct("6096d005b114a1167b11f5db");
        assertEquals(HttpStatus.OK, result.getStatusCode());

        result = productController.deleteProduct("6096c2582b5d843b03b66dc9");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

}
