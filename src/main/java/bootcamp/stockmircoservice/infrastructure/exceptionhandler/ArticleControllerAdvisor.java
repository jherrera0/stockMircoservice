package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleSortByEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleControllerAdvisor {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handleArticleNotFoundException(ArticleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArticleSortByInvalidException.class)
    public ResponseEntity<String> handleArticleSortByInvalidException(ArticleSortByInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleSortByEmptyException.class)
    public ResponseEntity<String> handleArticleSortByEmptyException(ArticleSortByEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleSortDirectionInvalidException.class)
    public ResponseEntity<String> handleArticleSortDirectionInvalidException(ArticleSortDirectionInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleSortDirectionEmptyException.class)
    public ResponseEntity<String> handleArticleSortDirectionEmptyException(ArticleSortDirectionEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleSizeEmptyException.class)
    public ResponseEntity<String> handleArticleSizeEmptyException(ArticleSizeEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticlePageEmptyException.class)
    public ResponseEntity<String> handleArticlePageEmptyException(ArticlePageEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleRequestNullException.class)
    public ResponseEntity<String> handleArticleRequestNullException(ArticleRequestNullException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticlePriceNullException.class)
    public ResponseEntity<String> handleArticlePriceNullException(ArticlePriceNullException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleNameEmptyException.class)
    public ResponseEntity<String> handleArticleNameEmptyException(ArticleNameEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleDescriptionEmptyException.class)
    public ResponseEntity<String> handleArticleDescriptionEmptyException(ArticleDescriptionEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleIllegalStockValueException.class)
    public ResponseEntity<String> handleArticleIllegalStockValueException(ArticleIllegalStockValueException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleBrandIdErrorException.class)
    public ResponseEntity<String> handleArticleBrandIdErrorException(ArticleBrandIdErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticlePriceNegativeException.class)
    public ResponseEntity<String> handleArticlePriceNegativeException(ArticlePriceNegativeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateCategoriesException.class)
    public ResponseEntity<String> handleDuplicateCategoriesException(DuplicateCategoriesException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoriesSizeException.class)
    public ResponseEntity<String> handleCategoriesSizeException(CategoriesSizeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
