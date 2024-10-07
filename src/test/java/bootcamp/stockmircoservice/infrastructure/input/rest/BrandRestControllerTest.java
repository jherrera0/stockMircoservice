package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BrandRestControllerTest {

    @Mock
    private IBrandHandler brandHandler;

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private BrandResponseMapper brandResponseMapper;

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
    void getBrands_withValidParameters_returnsBrandPage() {
        PageCustomResponse<BrandResponse> pageResponse = new PageCustomResponse<>();
        when(brandHandler.getAllBrands(0, 10, "asc")).thenReturn(pageResponse);

        ResponseEntity<PageCustomResponse<BrandResponse>> response = brandRestController.getBrands(0, 10, "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageResponse, response.getBody());
    }

    @Test
    void getBrands_withEmptyResult_returnsEmptyPage() {
        PageCustomResponse<BrandResponse> emptyPageResponse = new PageCustomResponse<>();
        emptyPageResponse.setItems(Collections.emptyList());
        when(brandHandler.getAllBrands(0, 10, "asc")).thenReturn(emptyPageResponse);

        ResponseEntity<PageCustomResponse<BrandResponse>> response = brandRestController.getBrands(0, 10, "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getItems().isEmpty());
    }

    @Test
    void getBrands_withNullSortDirection_returnsUnsortedPage() {
        PageCustomResponse<BrandResponse> pageResponse = new PageCustomResponse<>();
        when(brandHandler.getAllBrands(0, 10, null)).thenReturn(pageResponse);

        ResponseEntity<PageCustomResponse<BrandResponse>> response = brandRestController.getBrands(0, 10, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageResponse, response.getBody());
    }

}