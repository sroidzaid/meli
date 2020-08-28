package examen.meli.exception;

public class CurrencyServiceError extends RuntimeException{

    public CurrencyServiceError() {
        super("El servicio de informacion de monedas no está disponible");
    }
}
