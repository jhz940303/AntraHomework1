package org.week3.InClassAssignment.movies.model;

public class Movie {
  public String Title;
  public int Year;
  public String imdbID;

  public Movie(String title, int year, String imdbID) {
    Title = title;
    Year = year;
    this.imdbID = imdbID;
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public int getYear() {
    return Year;
  }

  public void setYear(int year) {
    Year = year;
  }

  public String getImdbID() {
    return imdbID;
  }

  public void setImdbID(String imdbID) {
    this.imdbID = imdbID;
  }
}
