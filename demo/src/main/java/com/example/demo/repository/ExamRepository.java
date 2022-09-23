package com.example.demo.repository;

import com.example.demo.entity.Exam;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
        List<Exam> findByCourse(String course);
    }

