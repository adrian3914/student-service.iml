package com.linkedIn.studentservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // method
    Student getStudentByName(String name);

    @Query("select avg (grade) from Student where active=true")
    Double getAvgGradeForActiveStudents();
}
