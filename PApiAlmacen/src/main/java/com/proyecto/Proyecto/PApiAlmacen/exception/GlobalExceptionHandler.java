
package com.proyecto.Proyecto.PApiAlmacen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.proyecto.Proyecto.PApiAlmacen.dto.ErrorResponseDTO;

@RestControllerAdvice  //Para interceptar lo que maneja por default el framework
                        //Se busca reemplazar lo que hizo el framework el modulo por default del manejo de excepciones
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFound(EntityNotFoundException ex){
        var httpStatus = HttpStatus.NOT_FOUND;
        var errorResponse = new ErrorResponseDTO(httpStatus, ex.getMessage(), ex.getStackTrace().toString());
        
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
