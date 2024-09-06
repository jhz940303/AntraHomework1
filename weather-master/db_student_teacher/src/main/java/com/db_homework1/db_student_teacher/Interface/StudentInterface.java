package com.db_homework1.db_student_teacher.Interface;

import com.db_homework1.db_student_teacher.DTO.StudentDTO;
import com.db_homework1.db_student_teacher.DTO.StudentNameDTO;
import java.util.List;

public interface StudentInterface {
  StudentDTO createStudent(StudentDTO studentDTO);
  StudentDTO getStudent(Long id);
  List<StudentDTO> getAllStudents();
  void deleteStudent(Long id);
  List<StudentNameDTO> findStudentsWithAtLeastNTeachers(int n);
}
