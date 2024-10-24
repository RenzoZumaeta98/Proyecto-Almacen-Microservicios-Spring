package com.proyecto.Proyecto.PApiProveedor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timeStamp;  //Fecha y hora del error
    private int code; //Numero del error
    private String status;  //
    private String message; //Mensaje que indica cuando se de el error 
    private String stackTrace;  //Detalle con=mpleto del error, norml. oculto

    
    public ErrorResponseDTO(HttpStatus httpStatus, String message, String stackTrace){
        this.timeStamp = new Date();
        this.message = message;
        this.stackTrace = stackTrace;
        this.code = httpStatus.value();
        this.status = httpStatus.name();
    }

}
