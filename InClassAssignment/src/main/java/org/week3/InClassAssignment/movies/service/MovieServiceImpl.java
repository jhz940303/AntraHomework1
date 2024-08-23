package org.week3.InClassAssignment.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.week3.InClassAssignment.movies.ApiConstants;
import org.week3.InClassAssignment.movies.model.MovieApiResponse;

@Service
public class MovieServiceImpl implements MovieService {
  private final RestTemplate restTemplate;

  @Autowired
  public MovieServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public MovieApiResponse getMoviesByPage(int page) {
    String url = buildUrl(page);
    return restTemplate.getForObject(url, MovieApiResponse.class);
  }

  @Override
  public int getMovieCountByYear(int year) {
    int totalMovies = 0;

    int page = 1;
    while (true) {
      String url = UriComponentsBuilder.fromHttpUrl(ApiConstants.MOVIE_API_URL)
          .queryParam("Year", year)
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



  private String buildUrl(int page) {
    return UriComponentsBuilder.fromHttpUrl(ApiConstants.MOVIE_API_URL)
        .queryParam("page", page)
        .toUriString();
  }
}
