package com.db_homework1.db_student_teacher.DTO;

import java.util.List;

public class StudentDTO {
  private Long id;
  private String name;
  private List<TeacherDTO> teachers;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<TeacherDTO> getTeachers() {
    return teachers;
  }

  public void setTeachers(List<TeacherDTO> teachers) {
    this.teachers = teachers;
  }
}
