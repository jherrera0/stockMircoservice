package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.category.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryControllerAdvisor {

    @ExceptionHandler(CategoryRequestNullException.class)
    public ResponseEntity<String> handleCategoryRequestNullException(CategoryRequestNullException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryPageInvalidException.class)
    public ResponseEntity<String> handleCategoryPageEmptyException(CategoryPageInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategorySizeInvalidException.class)
    public ResponseEntity<String> handleCategorySizeEmptyException(CategorySizeInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategorySortDirectionEmptyException.class)
    public ResponseEntity<String> handleCategorySortDirectionEmptyException(CategorySortDirectionEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategorySortDirectionInvalidException.class)
    public ResponseEntity<String> handleCategorySortDirectionInvalidException(CategorySortDirectionInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNullFieldException.class)
    public ResponseEntity<String> handleCategoryNullFieldException(CategoryNullFieldException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryRequestNegativeException.class)
    public ResponseEntity<String> handleCategoryRequestNegativeException(CategoryRequestNegativeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String>handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryDescriptionEmptyException.class)
    public ResponseEntity<String> handleCategoryDescriptionEmptyException(CategoryDescriptionEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryOversizeDescriptionException.class)
    public ResponseEntity<String> handleCategoryOversizeDescriptionException(CategoryOversizeDescriptionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryOversizeNameException.class)
    public ResponseEntity<String> handleCategoryOversizeNameException(CategoryOversizeNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNameEmptyException.class)
    public ResponseEntity<String> handleCategoryNameEmptyException(CategoryNameEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

