package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.BrandRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandPageInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandSizeInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandSortDirectionEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandSortDirectionInvalidException;
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
    void getAllBrands_ShouldThrowException_WhenPageIsNull() {
        assertThrows(BrandPageInvalidException.class, () -> brandHandler.getAllBrands(null, 10, "asc"));
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenPageIsNegative() {
        assertThrows(BrandPageInvalidException.class, () -> brandHandler.getAllBrands(-1, 10, "asc"));
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenSizeIsNull() {
        assertThrows(BrandSizeInvalidException.class, () -> brandHandler.getAllBrands(0, null, "asc"));
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenSizeIsNegative() {
        assertThrows(BrandSizeInvalidException.class, () -> brandHandler.getAllBrands(0, -1, "asc"));
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenSortDirectionIsNull() {
        assertThrows(BrandSortDirectionEmptyException.class, () -> brandHandler.getAllBrands(0, 10, null));
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenSortDirectionIsEmpty() {
        assertThrows(BrandSortDirectionEmptyException.class, () -> brandHandler.getAllBrands(0, 10, ""));
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenSortDirectionIsInvalid() {
        assertThrows(BrandSortDirectionInvalidException.class, () -> brandHandler.getAllBrands(0, 10, "invalid"));
    }

}
