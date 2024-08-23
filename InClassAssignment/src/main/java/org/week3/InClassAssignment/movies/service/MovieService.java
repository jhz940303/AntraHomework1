package org.week3.InClassAssignment.movies.service;

import org.week3.InClassAssignment.movies.model.MovieApiResponse;

public interface MovieService {
  MovieApiResponse getMoviesByPage(int page);
  int getMovieCountByYear(int year);
}
