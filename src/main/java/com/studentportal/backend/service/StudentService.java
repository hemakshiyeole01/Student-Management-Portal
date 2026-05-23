package com.studentportal.backend.service;

import com.studentportal.backend.entity.Student;
import com.studentportal.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentportal.backend.exception.StudentNotFoundException;
import com.studentportal.backend.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private StudentDTO convertToDTO(Student student) {

        StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setFullName(student.getFullName());
        dto.setEmail(student.getEmail());
        dto.setDepartment(student.getDepartment());
        dto.setYear(student.getYear());

        return dto;
    }

    // ADD STUDENT
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<StudentDTO> getAllStudentDTOs() {

        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // GET STUDENT BY ID
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with ID: " + id));
    }

    // UPDATE STUDENT
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with ID: " + id));

        existingStudent.setFullName(updatedStudent.getFullName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setYear(updatedStudent.getYear());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setAddress(updatedStudent.getAddress());
        existingStudent.setDob(updatedStudent.getDob());

        return studentRepository.save(existingStudent);
    }

    // DELETE STUDENT
    public String deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with ID: " + id));

        studentRepository.delete(student);

        return "Student deleted successfully";
    }
}