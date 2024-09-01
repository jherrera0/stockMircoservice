package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.category.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CategoryControllerAdvisorTest {

    @Test
    void handleCategoryAlreadyExistsException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryAlreadyExistsException ex = new CategoryAlreadyExistsException();

        ResponseEntity<String> response = advisor.handleCategoryAlreadyExistsException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category already exists", response.getBody());
    }
    @Test
    void handleCategoryNameEmptyException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryNameEmptyException ex = new CategoryNameEmptyException();

        ResponseEntity<String> response = advisor.handleCategoryNameEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The category name is empty", response.getBody());
    }

    @Test
    void handleCategoryDescriptionEmptyException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryDescriptionEmptyException ex = new CategoryDescriptionEmptyException();

        ResponseEntity<String> response = advisor.handleCategoryDescriptionEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The category description is empty", response.getBody());
    }

    @Test
    void handleCategoryOversizeDescriptionException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryOversizeDescriptionException ex = new CategoryOversizeDescriptionException();

        ResponseEntity<String> response = advisor.handleCategoryOversizeDescriptionException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The category description is too long", response.getBody());
    }

    @Test
    void handleCategoryOversizeNameException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryOversizeNameException ex = new CategoryOversizeNameException();

        ResponseEntity<String> response = advisor.handleCategoryOversizeNameException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The category name is too long", response.getBody());
    }
    @Test
    void handleCategoryRequestNullException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryRequestNullException ex = new CategoryRequestNullException();

        ResponseEntity<String> response = advisor.handleCategoryRequestNullException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category request cannot be null",
                response.getBody());
    }

    @Test
    void handleCategoryPageInvalidException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryPageInvalidException ex = new CategoryPageInvalidException();

        ResponseEntity<String> response = advisor.handleCategoryPageEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category page cannot be negative or null",
                response.getBody());
    }

    @Test
    void handleCategorySizeInvalidException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategorySizeInvalidException ex = new CategorySizeInvalidException();

        ResponseEntity<String> response = advisor.handleCategorySizeEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category size cannot be negative or null",
                response.getBody());
    }

    @Test
    void handleCategorySortDirectionEmptyException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategorySortDirectionEmptyException ex = new CategorySortDirectionEmptyException();

        ResponseEntity<String> response = advisor.handleCategorySortDirectionEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category sort direction cannot be empty or null",
                response.getBody());
    }

    @Test
    void handleCategorySortDirectionInvalidException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategorySortDirectionInvalidException ex = new CategorySortDirectionInvalidException();

        ResponseEntity<String> response = advisor.handleCategorySortDirectionInvalidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category sort direction is invalid, sort direction must be one of the following: asc, desc",
                response.getBody());
    }

    @Test
    void handleCategoryNullFieldException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryNullFieldException ex = new CategoryNullFieldException();

        ResponseEntity<String> response = advisor.handleCategoryNullFieldException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category cannot be null or have empty fields", response.getBody());
    }

    @Test
    void handleCategoryRequestNegativeException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryRequestNegativeException ex = new CategoryRequestNegativeException();

        ResponseEntity<String> response = advisor.handleCategoryRequestNegativeException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The page and size must be positive", response.getBody());
    }
}