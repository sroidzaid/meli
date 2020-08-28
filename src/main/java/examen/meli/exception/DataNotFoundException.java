package examen.meli.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException() {
        super("Datos no encontrados");
    }
}
