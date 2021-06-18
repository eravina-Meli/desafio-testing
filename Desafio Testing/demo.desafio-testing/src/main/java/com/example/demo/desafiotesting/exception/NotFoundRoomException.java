package com.example.demo.desafiotesting.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundRoomException extends RuntimeException{

    private String error;
    private HttpStatus status;

}
