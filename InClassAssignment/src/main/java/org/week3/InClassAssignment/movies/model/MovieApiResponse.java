package org.week3.InClassAssignment.movies.model;

import java.util.List;

public class MovieApiResponse {
  public int page;
  public int per_page;
  public int total;
  public int total_pages;
  public List<Movie> data;

  public MovieApiResponse(int page, int per_page, int total, int total_pages, List<Movie> data) {
    this.page = page;
    this.per_page = per_page;
    this.total = total;
    this.total_pages = total_pages;
    this.data = data;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPer_page() {
    return per_page;
  }

  public void setPer_page(int per_page) {
    this.per_page = per_page;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getTotal_pages() {
    return total_pages;
  }

  public void setTotal_pages(int total_pages) {
    this.total_pages = total_pages;
  }

  public List<Movie> getData() {
    return data;
  }

  public void setData(List<Movie> data) {
    this.data = data;
  }
}
