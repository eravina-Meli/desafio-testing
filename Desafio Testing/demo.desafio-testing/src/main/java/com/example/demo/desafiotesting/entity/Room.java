package com.example.demo.desafiotesting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String roomName;
    private double roomWidth;
    private double roomLength;
}
