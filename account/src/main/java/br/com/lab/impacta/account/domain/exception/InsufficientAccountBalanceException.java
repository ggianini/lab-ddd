package br.com.lab.impacta.account.domain.exception;

public class InsufficientAccountBalanceException extends RuntimeException {
    private String description;

    public String getDescription() {
        return description;
    }

    public InsufficientAccountBalanceException(){
        super();
    }

    public InsufficientAccountBalanceException(String message, String description){
        super(message);
        this.description = description;
    }

}
