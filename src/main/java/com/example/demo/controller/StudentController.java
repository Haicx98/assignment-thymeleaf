package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/students")
public class StudentController  {
    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET,value = "/register")
    public String creatStudent(Model model){
        model.addAttribute("student", new Student());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public String storeStudent(@Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        studentService.register(student);
        return "redirect:/students/list";
    }
    @GetMapping(value = "/detail/{id}")
    public String getDetail(@PathVariable("id") long id,Model model){
        Student student = studentService.findByID(id);
        model.addAttribute("student",student);
        return "detail";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String showListStudent(Model model){
        model.addAttribute("list",studentService.getList());
        return "list";
    }

}
