package com.db_homework1.db_student_teacher.Entity;

import com.db_homework1.db_student_teacher.Entity.Student;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "teacher")
  private List<Student_Teacher> studentTeachers = new ArrayList<>();

  public Teacher() {}

  public Teacher(String name) {
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

  // Convenience methods for managing relationships
  public void addStudent(Student student) {
    Student_Teacher studentTeacher = new Student_Teacher();
    studentTeacher.setTeacher(this);
    studentTeacher.setStudent(student);
    studentTeachers.add(studentTeacher);
    student.getStudentTeachers().add(studentTeacher);
  }

  public void removeStudent(Student student) {
    Student_Teacher studentTeacher = new Student_Teacher();
    studentTeacher.setTeacher(this);
    studentTeacher.setStudent(student);
    student.getStudentTeachers().remove(studentTeacher);
    studentTeachers.remove(studentTeacher);
  }
}
