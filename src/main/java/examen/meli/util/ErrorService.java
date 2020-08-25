package examen.meli.util;

public class ErrorService extends RuntimeException{

    private String code;

    public ErrorService(String code) {
        this.code = code;
    }

    public ErrorService(String message, String code) {
        super(message);
        this.code = code;
    }

    public ErrorService(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public ErrorService(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public ErrorService(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
