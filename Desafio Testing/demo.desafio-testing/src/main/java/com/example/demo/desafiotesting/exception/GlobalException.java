package com.example.demo.desafiotesting.exception;

import com.example.demo.desafiotesting.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    private ErrorResponseDTO errorResponseDTO;

    @ExceptionHandler(NotFoundRoomException.class)
    public ResponseEntity<ErrorResponseDTO> NoSuchFieldException(NotFoundRoomException e){
        errorResponseDTO = new ErrorResponseDTO(e.getError(),HttpStatus.BAD_GATEWAY);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException e){
        errorResponseDTO = new ErrorResponseDTO(e.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
    }
}
