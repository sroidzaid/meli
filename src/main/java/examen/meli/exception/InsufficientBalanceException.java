package examen.meli.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(Long accountId, BigDecimal amount) {
        super("Could not extract " + amount + " for account " + accountId);
    }

}