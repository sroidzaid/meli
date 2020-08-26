package examen.meli.exception;

public class ConexionErrorException extends RuntimeException{

    public ConexionErrorException() {
        super("Error al obtener los datos");
    }
}
