package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackages = {"com.example.demo"})  // scan JPA entities
public class MultipleCoreImplemSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(MultipleCoreImplemSampleApplication.class, args);
  }

}
