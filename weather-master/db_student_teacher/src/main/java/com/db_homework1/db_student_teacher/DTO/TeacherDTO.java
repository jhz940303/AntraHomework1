package com.db_homework1.db_student_teacher.DTO;

import java.util.List;

public class TeacherDTO {
  private Long id;
  private String name;
  private List<StudentDTO> students;

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

  public List<StudentDTO> getStudents() {
    return students;
  }

  public void setStudents(List<StudentDTO> students) {
    this.students = students;
  }
}
