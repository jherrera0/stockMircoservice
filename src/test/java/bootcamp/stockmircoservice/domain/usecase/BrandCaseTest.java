package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandCaseTest {


    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandCase brandCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsNull() {
        Brand brand = new Brand(null, "Description");
        assertThrows(BrandNullFieldException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsNull() {
        Brand brand = new Brand("Name", null);
        assertThrows(BrandNullFieldException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsEmpty() {
        Brand brand = new Brand("", "Description");
        assertThrows(BrandNameEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsBlank() {
        Brand brand = new Brand("   ", "Description");
        assertThrows(BrandNameEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsOversized() {
        String oversizedName = "a".repeat(Brand.MAX_NAME_LENGTH + 1);
        Brand brand = new Brand(oversizedName, "Description");
        assertThrows(BrandOversizeNameException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsEmpty() {
        Brand brand = new Brand("Name", "");
        assertThrows(BrandDescriptionEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsBlank() {
        Brand brand = new Brand("Name", "   ");
        assertThrows(BrandDescriptionEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsOversized() {
        String oversizedDescription = "a".repeat(Brand.MAX_DESCRIPTION_LENGTH + 1);
        Brand brand = new Brand("Name", oversizedDescription);
        assertThrows(BrandOversizeDescriptionException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandAlreadyExists() {
        Brand brand = new Brand("ExistingBrand", "Description");
        when(brandPersistencePort.findByName("ExistingBrand")).thenReturn(Optional.of(brand));
        assertThrows(BrandAlreadyExistsException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldSaveBrandSuccessfully_WhenBrandIsValid() {
        Brand brand = new Brand("NewBrand", "Description");
        when(brandPersistencePort.findByName("NewBrand")).thenReturn(Optional.empty());
        brandCase.saveBrand(brand);
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }
    @Test
    void getAllBrands_ShouldReturnEmptyList_WhenNoBrandsExist() {
        when(brandPersistencePort.getAllBrands(0, 10, "asc")).thenReturn(Collections.emptyList());

        List<Brand> result = brandCase.getAllBRands(0, 10, "asc");

        assertTrue(result.isEmpty());
        verify(brandPersistencePort).getAllBrands(0, 10, "asc");
    }

    @Test
    void getAllBrands_ShouldReturnBrandsList_WhenBrandsExist() {
        List<Brand> brands = Arrays.asList(new Brand("Brand1", "Description1"), new Brand("Brand2", "Description2"));
        when(brandPersistencePort.getAllBrands(0, 10, "asc")).thenReturn(brands);

        List<Brand> result = brandCase.getAllBRands(0, 10, "asc");

        assertEquals(brands, result);
        verify(brandPersistencePort).getAllBrands(0, 10, "asc");
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenPageIsNegative() {
        assertThrows(BrandRequestNegativeException.class, () -> brandCase.getAllBRands(-1, 10, "asc"));
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenSizeIsNegative() {
        assertThrows(BrandRequestNegativeException.class, () -> brandCase.getAllBRands(0, -1, "asc"));
    }

    @Test
    void getAllBrands_ShouldReturnBrandsList_WhenSortDirectionIsNull() {
        List<Brand> brands = Arrays.asList(new Brand("Brand1", "Description1"), new Brand("Brand2", "Description2"));
        when(brandPersistencePort.getAllBrands(0, 10, null)).thenReturn(brands);

        List<Brand> result = brandCase.getAllBRands(0, 10, null);

        assertEquals(brands, result);
        verify(brandPersistencePort).getAllBrands(0, 10, null);
    }
}