package com.db_homework1.db_student_teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DbStudentTeacherApplication {

  public static void main(String[] args) {
    SpringApplication.run(DbStudentTeacherApplication.class, args);
  }

}
