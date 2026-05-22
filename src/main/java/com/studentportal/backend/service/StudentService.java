package com.studentportal.backend.service;

import com.studentportal.backend.entity.Student;
import com.studentportal.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ADD STUDENT
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET STUDENT BY ID
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    // UPDATE STUDENT
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {

            existingStudent.setFullName(updatedStudent.getFullName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setDepartment(updatedStudent.getDepartment());
            existingStudent.setYear(updatedStudent.getYear());
            existingStudent.setPhone(updatedStudent.getPhone());
            existingStudent.setAddress(updatedStudent.getAddress());
            existingStudent.setDob(updatedStudent.getDob());

            return studentRepository.save(existingStudent);
        }

        return null;
    }

    // DELETE STUDENT
    public String deleteStudent(Long id) {

        studentRepository.deleteById(id);

        return "Student deleted successfully";
    }
}