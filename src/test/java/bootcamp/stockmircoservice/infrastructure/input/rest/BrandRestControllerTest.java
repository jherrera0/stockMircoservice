package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

class BrandRestControllerTest {

    @Mock
    private IBrandHandler brandHandler;

    @InjectMocks
    private BrandRestController brandRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_ShouldReturnCreatedStatus() {
        BrandRequest brandRequest = new BrandRequest();
        doNothing().when(brandHandler).saveBrand(brandRequest);

        ResponseEntity<Void> response = brandRestController.saveBrand(brandRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void saveBrand_ShouldReturnConflictStatus_WhenBrandAlreadyExists() {
        BrandRequest brandRequest = new BrandRequest();
        doThrow(new BrandAlreadyExistsException()).when(brandHandler).saveBrand(brandRequest);

        ResponseEntity<Void> response = brandRestController.saveBrand(brandRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void saveBrand_ShouldHandleException() {
        BrandRequest brandRequest = new BrandRequest();
        doThrow(new RuntimeException("Error")).when(brandHandler).saveBrand(brandRequest);

        ResponseEntity<Void> response = brandRestController.saveBrand(brandRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void saveBrand_ShouldReturnBadRequestStatus_WhenBrandRequestIsInvalid() {
        BrandRequest brandRequest = null;

        ResponseEntity<Void> response = brandRestController.saveBrand(brandRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}