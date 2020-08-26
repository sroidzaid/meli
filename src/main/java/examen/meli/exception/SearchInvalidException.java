package examen.meli.exception;

public class SearchInvalidException extends RuntimeException{

    public SearchInvalidException(String letra) {
        super("La búsqueda que desea realizar es inválida:" + letra);
    }

}