package com.proyecto.Proyecto.PApiProduct.exception;

import com.proyecto.Proyecto.PApiProduct.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException ex){
    
        var httpStatus = HttpStatus.NOT_FOUND;
        var errorResponse = new ErrorResponseDto(
                httpStatus,
                ex.getMessage(),
                ex.getStackTrace().toString());
        return new ResponseEntity<>(errorResponse,httpStatus);
    
    }
    
}
