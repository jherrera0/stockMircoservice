package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.BrandRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandHandlerTest {
    @Mock
    private BrandRequestMapper brandRequestMapper;

    @Mock
    private BrandResponseMapper brandResponseMapper;

    @Mock
    private IBrandServicePort brandServicePort;

    @InjectMocks
    private BrandHandler brandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void saveBrand_ShouldCallServicePort() {
        BrandRequest brandRequest = new BrandRequest();
        Brand brand = new Brand();
        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);
        doNothing().when(brandServicePort).saveBrand(brand);

        brandHandler.saveBrand(brandRequest);

        verify(brandServicePort, times(1)).saveBrand(brand);
    }


    @Test
    void getAllBrands_ShouldReturnBrandResponses() {
        List<Brand> brands = Collections.singletonList(new Brand());
        List<BrandResponse> brandResponses = Collections.singletonList(new BrandResponse());
        when(brandServicePort.getAllBRands(0, 10, "asc")).thenReturn(brands);
        when(brandResponseMapper.toResponseList(brands)).thenReturn(brandResponses);

        List<BrandResponse> result = brandHandler.getAllBrands(0, 10, "asc");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
    @Test
    void getAllBrands_ShouldReturnBrandResponses_WhenSortDirectionIsEmpty() {
        List<Brand> brands = Collections.singletonList(new Brand());
        List<BrandResponse> brandResponses = Collections.singletonList(new BrandResponse());
        when(brandServicePort.getAllBRands(0, 10, "")).thenReturn(brands);
        when(brandResponseMapper.toResponseList(brands)).thenReturn(brandResponses);

        List<BrandResponse> result = brandHandler.getAllBrands(0, 10, "");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
