package it.contrader.inbook.exception;

public class YearNotValidException extends RuntimeException{

    public YearNotValidException(String message){
        super(message);
    }
}