package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrandControllerAdvisorTest {

    @Test
    void handleBrandDescriptionEmptyException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandDescriptionEmptyException ex = new BrandDescriptionEmptyException();

        ResponseEntity<String> response = advisor.handleBrandDescriptionEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The brand description is empty", response.getBody());
    }

    @Test
    void handleBrandOversizeDescriptionException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandOversizeDescriptionException ex = new BrandOversizeDescriptionException();

        ResponseEntity<String> response = advisor.handleBrandOversizeDescriptionException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The brand description is too long", response.getBody());
    }

    @Test
    void handleBrandOversizeNameException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandOversizeNameException ex = new BrandOversizeNameException();

        ResponseEntity<String> response = advisor.handleBrandOversizeNameException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The brand name is too long", response.getBody());
    }

    @Test
    void handleBrandNameEmptyException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandNameEmptyException ex = new BrandNameEmptyException();

        ResponseEntity<String> response = advisor.handleBrandNameEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The brand name is empty", response.getBody());
    }
    @Test
    void handleBrandSortDirectionEmptyException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandSortDirectionEmptyException ex = new BrandSortDirectionEmptyException();

        ResponseEntity<String> response = advisor.handleBrandSortDirectionEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Brand sort direction cannot be empty or null", response.getBody());
    }

    @Test
    void handleBrandSortDirectionInvalidException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandSortDirectionInvalidException ex = new BrandSortDirectionInvalidException();

        ResponseEntity<String> response = advisor.handleBrandSortDirectionInvalidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Brand sort direction is invalid or null", response.getBody());
    }

    @Test
    void handleBrandPageInvalidException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandPageInvalidException ex = new BrandPageInvalidException();

        ResponseEntity<String> response = advisor.handleBrandPageInvalidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Brand page cannot be less than 1 or null", response.getBody());
    }

    @Test
    void handleBrandSizeInvalidException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandSizeInvalidException ex = new BrandSizeInvalidException();

        ResponseEntity<String> response = advisor.handleBrandSizeInvalidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Brand size is negative or null", response.getBody());
    }

    @Test
    void handleBrandNotFoundException_ShouldReturnNotFound() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandNotFoundException ex = new BrandNotFoundException();

        ResponseEntity<String> response = advisor.handleBrandNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Brand not found", response.getBody());
    }

    @Test
    void handleBrandRequestNegativeException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandRequestNegativeException ex = new BrandRequestNegativeException();

        ResponseEntity<String> response = advisor.handleBrandRequestNegativeException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The page and size must be positive", response.getBody());
    }

    @Test
    void handleBrandNullFieldException_ShouldReturnBadRequest() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandNullFieldException ex = new BrandNullFieldException();

        ResponseEntity<String> response = advisor.handleBrandNullFieldException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Brand cannot be null or have null fields", response.getBody());
    }

    @Test
    void handleBrandAlreadyExistsException_ShouldReturnConflict() {
        BrandControllerAdvisor advisor = new BrandControllerAdvisor();
        BrandAlreadyExistsException ex = new BrandAlreadyExistsException();

        ResponseEntity<String> response = advisor.handleBrandAlreadyExistsException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Brand already exists", response.getBody());
    }
}
