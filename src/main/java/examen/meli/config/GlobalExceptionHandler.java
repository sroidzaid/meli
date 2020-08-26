package examen.meli.config;

import examen.meli.exception.EntityCRUDException;
import examen.meli.exception.IpInvalidException;
import examen.meli.util.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * General crud validation response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = EntityCRUDException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> generalCRUDValidation(EntityCRUDException ex) {
        ErrorInfo error = new ErrorInfo(String.format("%s. entity: %s. id: %s", ex.getMessage(),
                ex.getEntityName(),
                ex.getEntityId()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(value = IpInvalidException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> ipInvalid(IpInvalidException ex) {
        ErrorInfo error = new ErrorInfo(String.format("%s %s IP Inválida", ex.getEntityName(), ex.getEntityId()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}