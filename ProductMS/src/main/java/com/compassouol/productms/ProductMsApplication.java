package com.compassouol.ProductMS;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductMsApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ProductMsApplication.class, args);

        SpringApplication app = new SpringApplication(ProductMsApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "9999"));
        app.run(args);
    }

}
