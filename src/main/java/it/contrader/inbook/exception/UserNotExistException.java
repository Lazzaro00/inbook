package it.contrader.inbook.exception;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String message){
        super(message);
    }
}
