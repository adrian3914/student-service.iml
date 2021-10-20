package com.linkedIn.studentservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor // removes the ball plate
@Data
@Entity // tell spring this is a bean
@NoArgsConstructor // default constructor
@Builder // desired constructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean active;
    private int grade;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
