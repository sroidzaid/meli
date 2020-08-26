package examen.meli.exception;

public class ApiException extends RuntimeException{

    public ApiException() {
        super("Error Inesperado en el servidor");
    }
}
