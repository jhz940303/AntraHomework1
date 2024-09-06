package com.db_homework1.db_student_teacher.Controller;

import com.db_homework1.db_student_teacher.DTO.StudentDTO;
import com.db_homework1.db_student_teacher.DTO.StudentNameDTO;
import com.db_homework1.db_student_teacher.Service.StudentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
    StudentDTO newStudent = studentService.createStudent(studentDTO);
    return ResponseEntity.ok(newStudent);
  }

  @GetMapping("/get")
  public ResponseEntity<StudentDTO> getStudentById(@RequestParam Long id) {
    StudentDTO student = studentService.getStudent(id);
    return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
  }

  @GetMapping("/list")
  public ResponseEntity<List<StudentDTO>> getAllStudents() {
    List<StudentDTO> students = studentService.getAllStudents();
    return students.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(students);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Void> deleteStudent(@RequestParam Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<?> getAllStudentsOrFiltered(@RequestParam(required = false) Integer minTeachers) {
    if (minTeachers != null) {
      System.out.println("Filtering students with at least " + minTeachers + " teachers.");
      List<StudentNameDTO> filteredStudents = studentService.findStudentsWithAtLeastNTeachers(minTeachers);
      return filteredStudents.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(filteredStudents);
    } else {
      System.out.println("Returning all students.");
      List<StudentDTO> allStudents = studentService.getAllStudents();
      return allStudents.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(allStudents);
    }
  }
}
