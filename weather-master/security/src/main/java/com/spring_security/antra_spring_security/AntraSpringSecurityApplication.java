package com.spring_security.antra_spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AntraSpringSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(AntraSpringSecurityApplication.class, args);
  }

}
