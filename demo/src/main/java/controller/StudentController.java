package controller;

import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import repository.StudentRepository;


@Controller
@RequestMapping("/students/")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/showForm/")
    public String showStudentForm(){
        return "add-student";
    }


    @GetMapping("list")
    public String students(Model model){
        model.addAttribute("students", this.studentRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addStudent(@Validated Student student, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-student";
        }
        this.studentRepository.save(student);
        return "redirect:list";
    }
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,Model model) {
        Student student = this.studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id:" + id ));

        model.addAttribute("student", student);
        return "update-student";

    }

    @GetMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id,@Validated Student student, BindingResult result, Model model){
        if(result.hasErrors()){
            student.setId(id);
            return "update-student";
        }
        //update student
        studentRepository.save(student);
        //get all students(with update)
        model.addAttribute("students", this.studentRepository.findAll());
        return "index";
    }
    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id,Model model){

        Student student = this.studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id:" + id ));
        this.studentRepository.delete(student);
        model.addAttribute("students",this.studentRepository.findAll());
        return "index";
    }





}
