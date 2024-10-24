package com.proyecto.Proyecto.PApiProduct.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy hh:mm:ss")
    private Date timeStamp;
    private int code;
    private String status;
    private String message;
    private String stackTrace;
    
    public ErrorResponseDto(
            HttpStatus httpStatus,
            String message,
            String stackTrace){
        this.timeStamp = new Date();
        this.message = message;
        this.stackTrace = stackTrace;
        this.code = httpStatus.value();
        this.status = httpStatus.name();
    
    }
}
