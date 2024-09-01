package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BrandControllerAdvisor {
    @ExceptionHandler(BrandSortDirectionEmptyException.class)
    public ResponseEntity<String> handleBrandSortDirectionEmptyException(BrandSortDirectionEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandSortDirectionInvalidException.class)
    public ResponseEntity<String> handleBrandSortDirectionInvalidException(BrandSortDirectionInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BrandPageInvalidException.class)
    public ResponseEntity<String> handleBrandPageInvalidException(BrandPageInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BrandSizeInvalidException.class)
    public ResponseEntity<String> handleBrandSizeInvalidException(BrandSizeInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<String> handleBrandNotFoundException(BrandNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BrandRequestNegativeException.class)
    public ResponseEntity<String> handleBrandRequestNegativeException(BrandRequestNegativeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

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

