package org.week3.InClassAssignment.movies.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.week3.InClassAssignment.movies.model.Movie;
import org.week3.InClassAssignment.movies.model.MovieApiResponse;
import org.week3.InClassAssignment.movies.responseDTO.ResponseMovieDTO;
import org.week3.InClassAssignment.movies.service.MovieService;

@RestController
public class MovieController {
  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/movies/page")
  public MovieApiResponse getMoviesByPage(@RequestParam int page) {
    return movieService.getMoviesByPage(page);
  }

  @GetMapping("/movies/year")
  public ResponseMovieDTO getMoviesByYear(@RequestParam int year) {
    int count = movieService.getMovieCountByYear(year);

    return new ResponseMovieDTO(count, year);
  }
}
