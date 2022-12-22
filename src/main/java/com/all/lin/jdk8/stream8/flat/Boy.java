package com.all.lin.jdk8.stream8.flat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Boy {
    private String name;

    private int age;

    private List<Toy> toys;
}