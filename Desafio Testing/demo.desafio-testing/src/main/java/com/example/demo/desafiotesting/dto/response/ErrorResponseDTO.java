package com.example.demo.desafiotesting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private HttpStatus httpStatus;
}
