package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Brand;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IBrandServicePortTest {
    @Test
    void saveBrand_ShouldSaveBrandSuccessfully() {
        IBrandServicePort brandServicePort = mock(IBrandServicePort.class);
        Brand brand = new Brand();

        brandServicePort.saveBrand(brand);

        verify(brandServicePort, times(1)).saveBrand(brand);
    }

    @Test
    void getAllBRands_ShouldReturnEmptyList_WhenNoBrandsExist() {
        IBrandServicePort brandServicePort = mock(IBrandServicePort.class);
        when(brandServicePort.getAllBRands(0, 10, "asc")).thenReturn(Collections.emptyList());

        List<Brand> brands = brandServicePort.getAllBRands(0, 10, "asc");

        assertTrue(brands.isEmpty());
    }

    @Test
    void getAllBRands_ShouldReturnBrandsList_WhenBrandsExist() {
        List<Brand> expectedBrands = List.of(new Brand());
        IBrandServicePort brandServicePort = mock(IBrandServicePort.class);
        when(brandServicePort.getAllBRands(0, 10, "asc")).thenReturn(expectedBrands);

        List<Brand> brands = brandServicePort.getAllBRands(0, 10, "asc");

        assertEquals(expectedBrands, brands);
    }
}