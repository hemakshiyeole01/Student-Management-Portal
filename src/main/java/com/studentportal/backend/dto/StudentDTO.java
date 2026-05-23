package com.studentportal.backend.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private Long id;

    private String fullName;

    private String email;

    private String department;

    private int year;
}