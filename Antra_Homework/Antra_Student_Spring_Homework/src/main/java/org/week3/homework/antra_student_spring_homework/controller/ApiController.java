package org.week3.homework.antra_student_spring_homework.controller;

import java.util.Arrays;
import java.util.List;
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
  public List<UniversityResponse> fetchUniversitiesByCountries(@RequestParam String countries) {
    List<String> countryList = Arrays.asList(countries.split(","));
    return apiService.getUniversitiesByCountries(countryList);
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
