package online.renjilin;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducterApplication.class);
    }
}
