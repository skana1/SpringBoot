package com.example.demo.controller;

import com.example.demo.entity.Exam;
import com.example.demo.entity.Student;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam/")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;


    @GetMapping("showForm")
    public String showExamForm(Exam exam) {
        return "add-exam";
    }

    @GetMapping("list")
    public String exam(Model model) {
        model.addAttribute("exam", this.examRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addExam(@Validated Exam exam, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-exam";
        }

        this.examRepository.save(exam);
        return "redirect:list";
    }


    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable ("id") long id, Model model) {
        Exam exam = (Exam) this.examRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));

        model.addAttribute("exam", exam);
        return "update-student";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Validated Exam exam, BindingResult result, Model model) {
        if(result.hasErrors()) {
            exam.setId(id);
            return "update-student";
        }

        // update student
        examRepository.save(exam);

        // get all students ( with update)
        model.addAttribute("students", this.examRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable ("id") long id, Model model) {

        Exam exam = (Exam) this.examRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));

        this.examRepository.delete(exam);
        model.addAttribute("exam", this.examRepository.findAll());
        return "index";

    }
}
