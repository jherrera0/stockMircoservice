package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.article.DuplicateCategoriesException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandNullFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleControllerAdvisor {

    @ExceptionHandler(DuplicateCategoriesException.class)
    public ResponseEntity<String> handleDuplicateCategoriesException(DuplicateCategoriesException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
