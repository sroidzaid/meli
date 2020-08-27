package examen.meli.config;

import examen.meli.exception.ApiException;
import examen.meli.exception.ConexionErrorException;
import examen.meli.exception.DataNotFoundException;
import examen.meli.exception.IpInvalidException;
import examen.meli.util.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = IpInvalidException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> ipIsNotValid(IpInvalidException ex) {
        ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), String.format("%s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> apiError(ApiException ex) {
        ErrorInfo error = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("%s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(value = ConexionErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> conexionError(ConexionErrorException ex) {
        ErrorInfo error = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("%s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> dataNotFound(DataNotFoundException ex) {
        ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), String.format("%s", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



}