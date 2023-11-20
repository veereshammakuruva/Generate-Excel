package com.demo.collegemanagement.service;

import com.demo.collegemanagement.model.Student;
import com.demo.collegemanagement.repository.StudentRepository;
import com.demo.collegemanagement.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExcelUtil excelUtil;

    public Student createStudent(Student student){
        return this.studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        var students = this.studentRepository.findAll();
        this.excelUtil.generateExcelFile(students, response);
    }
}
