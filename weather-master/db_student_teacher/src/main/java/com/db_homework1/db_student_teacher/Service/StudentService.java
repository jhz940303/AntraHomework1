package com.db_homework1.db_student_teacher.Service;

import com.db_homework1.db_student_teacher.DTO.StudentDTO;
import com.db_homework1.db_student_teacher.DTO.StudentNameDTO;
import com.db_homework1.db_student_teacher.DTO.TeacherDTO;
import com.db_homework1.db_student_teacher.Entity.Student;
import com.db_homework1.db_student_teacher.Entity.Student_Teacher;
import com.db_homework1.db_student_teacher.Entity.Teacher;
import com.db_homework1.db_student_teacher.Interface.StudentInterface;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentInterface {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public StudentDTO createStudent(StudentDTO studentDTO) {
    Student student = new Student();
    student.setName(studentDTO.getName());
    entityManager.persist(student);
    return convertToStudentDTO(student);
  }

  @Override
  @Transactional
  public StudentDTO getStudent(Long id) {
    Student student = entityManager.find(Student.class, id);
    return student != null ? convertToStudentDTO(student) : null;
  }

  @Override
  @Transactional
  public List<StudentDTO> getAllStudents() {
    List<Student> students = entityManager.createQuery("select s from Student s", Student.class).getResultList();
    return students.stream().map(this::convertToStudentDTO).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void deleteStudent(Long id) {
    Student student = entityManager.find(Student.class, id);
    if (student != null) {
      entityManager.remove(student);
    }
  }

  @Override
  @Transactional
  public List<StudentNameDTO> findStudentsWithAtLeastNTeachers(int n) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
    Root<Student> student = cq.from(Student.class);

    Join<Student, Student_Teacher> studentTeachers = student.join("studentTeachers");
    Join<Student_Teacher, Teacher> teachers = studentTeachers.join("teacher");

    Expression<Long> countN = cb.literal((long) n);

    cq.select(student).groupBy(student.get("id")).having(cb.ge(cb.count(teachers.get("id")), countN));

    List<Student> students = entityManager.createQuery(cq).getResultList();
    return students.stream()
        .map(s -> new StudentNameDTO(s.getName()))
        .collect(Collectors.toList());
  }



  private StudentDTO convertToStudentDTO(Student student) {
    StudentDTO dto = new StudentDTO();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setTeachers(student.getStudentTeachers().stream()
        .map(st -> {
          TeacherDTO simpleDto = new TeacherDTO();
          simpleDto.setId(st.getTeacher().getId());
          simpleDto.setName(st.getTeacher().getName());
          return simpleDto;
        })
        .collect(Collectors.toList()));
    return dto;
  }

  private TeacherDTO convertToTeacherDTO(Teacher teacher) {
    TeacherDTO dto = new TeacherDTO();
    dto.setId(teacher.getId());
    dto.setName(teacher.getName());
    dto.setStudents(teacher.getStudentTeachers().stream()
        .map(st -> {
          StudentDTO simpleDto = new StudentDTO();
          simpleDto.setId(st.getStudent().getId());
          simpleDto.setName(st.getStudent().getName());
          return simpleDto;
        })
        .collect(Collectors.toList()));
    return dto;
  }

}