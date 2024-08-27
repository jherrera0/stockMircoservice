package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandCaseTest {
    private BrandCase brandCase;
    private IBrandPersistencePort brandPersistencePort;

    @BeforeEach
    void setUp() {
        brandPersistencePort = mock(IBrandPersistencePort.class);
        brandCase = new BrandCase(brandPersistencePort);
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsEmpty() {
        Brand brand = new Brand();
        brand.setName("");
        brand.setDescription("Valid description");

        assertThrows(BrandNameEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsBlank() {
        Brand brand = new Brand();
        brand.setName(" ");
        brand.setDescription("Valid description");

        assertThrows(BrandNameEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsTooLong() {
        Brand brand = new Brand();
        brand.setName("A".repeat(Brand.MAX_NAME_LENGTH + 1));
        brand.setDescription("Valid description");

        assertThrows(BrandOversizeNameException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsEmpty() {
        Brand brand = new Brand();
        brand.setName("Valid name");
        brand.setDescription("");

        assertThrows(BrandDescriptionEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsBlank() {
        Brand brand = new Brand();
        brand.setName("Valid name");
        brand.setDescription(" ");

        assertThrows(BrandDescriptionEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsTooLong() {
        Brand brand = new Brand();
        brand.setName("Valid name");
        brand.setDescription("A".repeat(Brand.MAX_DESCRIPTION_LENGTH + 1));

        assertThrows(BrandOversizeDescriptionException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandAlreadyExists() {
        Brand brand = new Brand();
        brand.setName("Existing name");
        brand.setDescription("Valid description");
        when(brandPersistencePort.findByName("Existing name")).thenReturn(Optional.of(brand));

        assertThrows(BrandAlreadyExistsException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_ShouldSaveBrand_WhenBrandIsValid() {
        Brand brand = new Brand();
        brand.setName("Valid name");
        brand.setDescription("Valid description");
        when(brandPersistencePort.findByName("Valid name")).thenReturn(Optional.empty());

        brandCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }
    @Test
    void getAllBrands_ShouldReturnEmptyList_WhenNoBrandsExist() {
        when(brandPersistencePort.getAllBrands(0, 10, "asc")).thenReturn(Collections.emptyList());

        List<Brand> result = brandCase.getAllBRands(0, 10, "asc");

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllBrands_ShouldReturnBrands_WhenBrandsExist() {
        List<Brand> brands = Arrays.asList(new Brand("Brand1", "Description1"), new Brand("Brand2", "Description2"));
        when(brandPersistencePort.getAllBrands(0, 10, "asc")).thenReturn(brands);

        List<Brand> result = brandCase.getAllBRands(0, 10, "asc");

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Brand1", result.get(0).getName());
        assertEquals("Brand2", result.get(1).getName());
    }

    @Test
    void getAllBrands_ShouldReturnBrands_WhenSortDirectionIsEmpty() {
        List<Brand> brands = Arrays.asList(new Brand("Brand1", "Description1"), new Brand("Brand2", "Description2"));
        when(brandPersistencePort.getAllBrands(0, 10, "")).thenReturn(brands);

        List<Brand> result = brandCase.getAllBRands(0, 10, "");

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Brand1", result.get(0).getName());
        assertEquals("Brand2", result.get(1).getName());
    }
}