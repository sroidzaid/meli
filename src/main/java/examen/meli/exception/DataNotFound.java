package examen.meli.exception;

public class DataNotFound extends RuntimeException{

    public DataNotFound() {
        super("Datos no encontrados para esa IP");
    }
}
