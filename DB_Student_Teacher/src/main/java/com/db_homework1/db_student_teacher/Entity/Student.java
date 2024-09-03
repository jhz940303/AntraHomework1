package com.db_homework1.db_student_teacher.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "student")
  private List<Student_Teacher> studentTeachers = new ArrayList<>();

  public Student(){}

  public Student(String name) {
    this.name = name;
  }

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

  public List<Student_Teacher> getStudentTeachers() {
    return studentTeachers;
  }

  public void setStudentTeachers(List<Student_Teacher> studentTeachers) {
    this.studentTeachers = studentTeachers;
  }

  public void addTeacher(Teacher teacher) {
    Student_Teacher studentTeacher = new Student_Teacher();
    studentTeacher.setStudent(this);
    studentTeacher.setTeacher(teacher);
    studentTeachers.add(studentTeacher);
    teacher.getStudentTeachers().add(studentTeacher);
  }

  public void removeTeacher(Teacher teacher) {
    Student_Teacher studentTeacher = new Student_Teacher();
    studentTeacher.setStudent(this);
    studentTeacher.setTeacher(teacher);
    teacher.getStudentTeachers().remove(studentTeacher);
    studentTeachers.remove(studentTeacher);
  }
}
