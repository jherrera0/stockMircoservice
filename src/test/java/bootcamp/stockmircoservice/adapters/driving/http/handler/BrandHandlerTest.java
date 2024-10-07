package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.BrandRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IPageCustomResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandHandlerTest {
    @Mock
    private BrandRequestMapper brandRequestMapper;

    @Mock
    private IPageCustomResponseMapper pageCustomResponseMapper;

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
    void getAllBrands_withValidParameters_returnsPageCustomResponse() {
        List<Brand> brands = List.of(new Brand());
        PageCustom<Brand> pageCustom = new PageCustom<>(1, 10, 1, brands);
        PageCustomResponse<BrandResponse> pageCustomResponse = new PageCustomResponse<>();
        when(brandServicePort.getAllBRands(1, 10, "asc")).thenReturn(pageCustom);
        when(pageCustomResponseMapper.toResponsePage(pageCustom)).thenReturn(pageCustomResponse);

        PageCustomResponse<BrandResponse> result = brandHandler.getAllBrands(1, 10, "asc");

        assertEquals(pageCustomResponse, result);
    }

    @Test
    void getAllBrands_withNullSortDirection_returnsPageCustomResponse() {
        List<Brand> brands = List.of(new Brand());
        PageCustom<Brand> pageCustom = new PageCustom<>(1, 10, 1, brands);
        PageCustomResponse<BrandResponse> pageCustomResponse = new PageCustomResponse<>();
        when(brandServicePort.getAllBRands(1, 10, null)).thenReturn(pageCustom);
        when(pageCustomResponseMapper.toResponsePage(pageCustom)).thenReturn(pageCustomResponse);

        PageCustomResponse<BrandResponse> result = brandHandler.getAllBrands(1, 10, null);

        assertEquals(pageCustomResponse, result);
    }

    @Test
    void getAllBrands_withEmptyResult_returnsEmptyPageCustomResponse() {
        PageCustom<Brand> pageCustom = new PageCustom<>(1, 10, 1, Collections.emptyList());
        PageCustomResponse<BrandResponse> pageCustomResponse = new PageCustomResponse<>();
        when(brandServicePort.getAllBRands(1, 10, "asc")).thenReturn(pageCustom);
        when(pageCustomResponseMapper.toResponsePage(pageCustom)).thenReturn(pageCustomResponse);

        PageCustomResponse<BrandResponse> result = brandHandler.getAllBrands(1, 10, "asc");

        assertEquals(pageCustomResponse, result);
    }
}
