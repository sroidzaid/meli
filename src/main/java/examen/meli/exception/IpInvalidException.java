package examen.meli.exception;


public class IpInvalidException extends RuntimeException{

	public IpInvalidException() {
		super("La ip es inválida o tiene formato erróneo");

	}

}
