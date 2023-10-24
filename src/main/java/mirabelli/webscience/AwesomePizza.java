package mirabelli.webscience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "mirabelli.webscience")
public class AwesomePizza {
    public static void main(String[] args) {
        SpringApplication.run(AwesomePizza.class, args);
    }
}