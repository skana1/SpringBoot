package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "course")
    private String course;

    @Column(name = "marks")
    private long marks;


    public Exam() {
        super();
    }

    public Exam(String course, long marks) {
        super();
        this.course = course;
        this.marks = marks;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public long getMarks() {
        return marks;
    }
    public void setMarks(long marks) {
        this.marks = marks;
    }

}