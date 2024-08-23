package org.week3.InClassAssignment.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.week3.InClassAssignment.movies.model.MovieApiResponse;

@Service
public class MovieService {
  private final RestTemplate restTemplate;

  @Autowired
  public MovieService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public MovieApiResponse getMoviesByPage(int page) {
    String url = UriComponentsBuilder.fromHttpUrl("https://jsonmock.hackerrank.com/api/movies")
        .queryParam("page", page)
        .toUriString();

    return restTemplate.getForObject(url, MovieApiResponse.class);
  }

  public int getMovieCountByYear(int year) {
    int totalMovies = 0;

    int page = 1;
    while (true) {
      String url = UriComponentsBuilder.fromHttpUrl("https://jsonmock.hackerrank.com/api/movies")
          .queryParam("Year", year)
          .queryParam("page", page)
          .toUriString();

      MovieApiResponse response = restTemplate.getForObject(url, MovieApiResponse.class);
      if (response == null || response.getData() == null || response.getData().isEmpty()) {
        break;
      }

      totalMovies += response.getData().size();

      if (page >= response.getTotal()) {
        break;
      }

      page++;

    }

    return totalMovies;
  }
}
