package it.contrader.inbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler({EmailAlreadyExistException.class, InvalidGenderException.class})
    public ResponseEntity<?> ConflictException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({RoleNotFoundException.class, UserNotExistException.class})
    public ResponseEntity<?> RoleNotFoundExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> PasswordIncorrectExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
    }


}
