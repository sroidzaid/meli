package examen.meli.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import examen.meli.exception.ConexionErrorException;
import examen.meli.exception.IpInvalidException;

public class ErrorInfo {

	@JsonProperty("message")
	private String message;
	@JsonProperty("status_code")
	private int statusCode;


	public ErrorInfo(int statusCode, String message) {
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}



}
