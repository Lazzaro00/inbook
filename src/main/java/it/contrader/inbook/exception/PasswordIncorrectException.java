package it.contrader.inbook.exception;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String message){
        super(message);
    }
}
