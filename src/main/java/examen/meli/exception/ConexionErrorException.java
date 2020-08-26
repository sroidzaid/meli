package examen.meli.exception;

public class ConexionErrorException extends RuntimeException{

    public ConexionErrorException(String letra) {
        super("Error al obtener los datos");
    }
}
