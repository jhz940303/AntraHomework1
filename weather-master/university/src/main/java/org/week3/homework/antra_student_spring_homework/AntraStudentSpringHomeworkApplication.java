package org.week3.homework.antra_student_spring_homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AntraStudentSpringHomeworkApplication {

  public static void main(String[] args) {
    SpringApplication.run(AntraStudentSpringHomeworkApplication.class, args);
  }

}