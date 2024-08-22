package org.week3.homework.antra_student_spring_homework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.week3.homework.antra_student_spring_homework.model.UniversityResponse;
import org.week3.homework.antra_student_spring_homework.service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {
  private final ApiService apiService;

  public ApiController(ApiService apiService) {
    this.apiService = apiService;
  }

  @GetMapping("/universities")
  public UniversityResponse fetchUniversitiesByCountry(@RequestParam String country) {
    return apiService.getUniversitiesByCountry(country);
  }

  @GetMapping("/search")
  public UniversityResponse searchUniversities(@RequestParam String query) {
    return apiService.searchUniversities(query);
  }

  @GetMapping("/status")
  public String status() {
    return "API is up and running";
  }
}
