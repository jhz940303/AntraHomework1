package org.week3.homework.antra_student_spring_homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.week3.homework.antra_student_spring_homework.model.UniversityResponse;

@Service
public class ApiService {
  private final RestTemplate restTemplate;

  public ApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public UniversityResponse getUniversitiesByCountry(String country) {
    String url = "http://universities.hipolabs.com/search?country=" + country;
    String response = restTemplate.getForObject(url, String.class);
    return new UniversityResponse(response);
  }

  public UniversityResponse searchUniversities(String query) {
    String url = "http://universities.hipolabs.com/search?" + query;
    String response = restTemplate.getForObject(url, String.class);
    return new UniversityResponse(response);
  }
}
