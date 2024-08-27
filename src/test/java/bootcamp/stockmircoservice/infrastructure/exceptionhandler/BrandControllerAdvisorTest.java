package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandControllerAdvisorTest {

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
}
