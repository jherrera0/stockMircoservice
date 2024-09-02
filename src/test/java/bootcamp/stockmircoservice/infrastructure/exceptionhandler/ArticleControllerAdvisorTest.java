package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;

import static org.junit.jupiter.api.Assertions.*;

class ArticleControllerAdvisorTest {
    @Test
    void handleArticleSortByInvalidException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleSortByInvalidException ex = new ArticleSortByInvalidException();

        ResponseEntity<String> response = advisor.handleArticleSortByInvalidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article sort by invalid, sort by must be one of the following: name, brand, categories.name", response.getBody());
    }

    @Test
    void handleArticleSortByEmptyException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleSortByEmptyException ex = new ArticleSortByEmptyException();

        ResponseEntity<String> response = advisor.handleArticleSortByEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article sort by cannot be empty or null", response.getBody());
    }

    @Test
    void handleArticleSortDirectionInvalidException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleSortDirectionInvalidException ex = new ArticleSortDirectionInvalidException();

        ResponseEntity<String> response = advisor.handleArticleSortDirectionInvalidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article sort direction is invalid, sort direction must be one of the following: asc, desc", response.getBody());
    }

    @Test
    void handleArticleSortDirectionEmptyException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleSortDirectionEmptyException ex = new ArticleSortDirectionEmptyException();

        ResponseEntity<String> response = advisor.handleArticleSortDirectionEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article sort direction cannot be empty or null", response.getBody());
    }

    @Test
    void handleArticleSizeEmptyException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleSizeEmptyException ex = new ArticleSizeEmptyException();

        ResponseEntity<String> response = advisor.handleArticleSizeEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article size cannot be negative, empty or null", response.getBody());
    }

    @Test
    void handleArticlePageEmptyException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticlePageEmptyException ex = new ArticlePageEmptyException();

        ResponseEntity<String> response = advisor.handleArticlePageEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article page cannot be empty or less than 0", response.getBody());
    }

    @Test
    void handleArticleRequestNullException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleRequestNullException ex = new ArticleRequestNullException();

        ResponseEntity<String> response = advisor.handleArticleRequestNullException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article request cannot be null", response.getBody());
    }

    @Test
    void handleArticlePriceNullException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticlePriceNullException ex = new ArticlePriceNullException();

        ResponseEntity<String> response = advisor.handleArticlePriceNullException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article price cannot be null", response.getBody());
    }

    @Test
    void handleArticleNameEmptyException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleNameEmptyException ex = new ArticleNameEmptyException();

        ResponseEntity<String> response = advisor.handleArticleNameEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article name cannot be empty", response.getBody());
    }

    @Test
    void handleArticleDescriptionEmptyException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleDescriptionEmptyException ex = new ArticleDescriptionEmptyException();

        ResponseEntity<String> response = advisor.handleArticleDescriptionEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article description cannot be empty", response.getBody());
    }

    @Test
    void handleArticleIllegalStockValueException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleIllegalStockValueException ex = new ArticleIllegalStockValueException();

        ResponseEntity<String> response = advisor.handleArticleIllegalStockValueException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article stock cannot be negative or null", response.getBody());
    }

    @Test
    void handleArticleBrandIdErrorException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticleBrandIdErrorException ex = new ArticleBrandIdErrorException();

        ResponseEntity<String> response = advisor.handleArticleBrandIdErrorException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article brand id cannot be negative, zero or empty", response.getBody());
    }

    @Test
    void handleArticlePriceNegativeException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        ArticlePriceNegativeException ex = new ArticlePriceNegativeException();

        ResponseEntity<String> response = advisor.handleArticlePriceNegativeException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article price cannot be negative", response.getBody());
    }

    @Test
    void handleDuplicateCategoriesException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        DuplicateCategoriesException ex = new DuplicateCategoriesException();

        ResponseEntity<String> response = advisor.handleDuplicateCategoriesException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Duplicate categories are not allowed", response.getBody());
    }

    @Test
    void handleCategoriesSizeException_ShouldReturnBadRequest() {
        ArticleControllerAdvisor advisor = new ArticleControllerAdvisor();
        CategoriesSizeException ex = new CategoriesSizeException();

        ResponseEntity<String> response = advisor.handleCategoriesSizeException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The article must have between 1 and 3 categories", response.getBody());
    }

}