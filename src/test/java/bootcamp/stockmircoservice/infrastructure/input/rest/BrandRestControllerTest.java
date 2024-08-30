package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
    void getAllBrands_ShouldReturnEmptyList_WhenNoBrandsExist() {
        when(brandServicePort.getAllBRands(0, 10, "asc")).thenReturn(Collections.emptyList());
        when(brandResponseMapper.toResponseList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<BrandResponse> result = brandHandler.getAllBrands(0, 10, "asc");

        assertTrue(result.isEmpty());
    }
}