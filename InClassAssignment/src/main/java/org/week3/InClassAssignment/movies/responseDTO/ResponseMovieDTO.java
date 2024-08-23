package org.week3.InClassAssignment.movies.responseDTO;

public class ResponseMovieDTO {
  public int totalMovieInThisYear;
  public int year;

  public ResponseMovieDTO(int totalMovieInThisYear, int year) {
    this.totalMovieInThisYear = totalMovieInThisYear;
    this.year = year;
  }
}
