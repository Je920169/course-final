package com.example.demo.model.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String grade;
    private String major;
}