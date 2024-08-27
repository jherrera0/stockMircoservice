package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BrandControllerAdvisor {
    @ExceptionHandler(BrandNullFieldException.class)
    public ResponseEntity<String> handleBrandNullFieldException(BrandNullFieldException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<String>handleBrandAlreadyExistsException(BrandAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BrandDescriptionEmptyException.class)
    public ResponseEntity<String> handleBrandDescriptionEmptyException(BrandDescriptionEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandOversizeDescriptionException.class)
    public ResponseEntity<String> handleBrandOversizeDescriptionException(BrandOversizeDescriptionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandOversizeNameException.class)
    public ResponseEntity<String> handleBrandOversizeNameException(BrandOversizeNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandNameEmptyException.class)
    public ResponseEntity<String> handleBrandNameEmptyException(BrandNameEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

