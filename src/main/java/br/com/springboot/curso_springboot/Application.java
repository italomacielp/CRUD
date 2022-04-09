package br.com.springboot.curso_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 * Spring Boot application starter class
 */

@EntityScan(basePackages = "br.com.springboot.curso_springboot.model")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); /* Linha principal
         Responsável em rodar a aplicação Java Spring Boot*/
    }
}
