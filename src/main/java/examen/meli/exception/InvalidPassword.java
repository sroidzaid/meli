package examen.meli.exception;

public class InvalidPassword extends RuntimeException {

    public InvalidPassword(String UserId) {
        super("Invalid Password " + UserId);
    }

}