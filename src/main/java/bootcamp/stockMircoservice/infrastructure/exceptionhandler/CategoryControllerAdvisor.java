package bootcamp.stockMircoservice.infrastructure.exceptionhandler;

import bootcamp.stockMircoservice.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryControllerAdvisor {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        ResponseEntity responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(CategoryDescriptionEmptyException.class)
    public ResponseEntity<String> handleCategoryDescriptionEmptyException(CategoryDescriptionEmptyException ex) {
        ResponseEntity responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(CategoryOversizeDescriptionException.class)
    public ResponseEntity<String> handleCategoryOversizeDescriptionException(CategoryOversizeDescriptionException ex) {
        ResponseEntity responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(CategoryOversizeNameException.class)
    public ResponseEntity<String> handleCategoryOversizeNameException(CategoryOversizeNameException ex) {
        ResponseEntity responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(CategoryNameEmptyException.class)
    public ResponseEntity<String> handleCategoryNameEmptyException(CategoryNameEmptyException ex) {
        ResponseEntity responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

