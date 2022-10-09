package com.syk.contentservice.handler;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 沈永康
 * @Date 2022/10/4 16:15
 * @Version 1.0
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionErrorHandler {
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity error(SecurityException e){
        log.warn("发生 SecurityException 异常",e);
        return new ResponseEntity(
                ErrorBody.builder().body(e.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).build(),HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity error(NullPointerException e){
        log.warn("发生 NullPointerException 异常",e);
        return new ResponseEntity(
                ErrorBody.builder().body(e.getMessage()).status(HttpStatus.OK.value()).build(),HttpStatus.OK
        );
    }
}
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ErrorBody {
   private String body;
   private Integer status;
}
