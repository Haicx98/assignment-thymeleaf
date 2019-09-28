package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Student register(Student student){
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        student.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        student.setStatus(1);
        return studentRepository.save(student);
    }

    public Student findByID(long id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student findByEmail(String email){
        return studentRepository.findByEmail(email);
    }
    public List<Student> getList(){
        return studentRepository.findAll();
    }

}
