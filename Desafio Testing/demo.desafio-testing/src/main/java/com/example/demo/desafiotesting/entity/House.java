package com.example.demo.desafiotesting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
    private String propName;
    private Neighborhood neighborhood;
    private List<Room> rooms;
}
