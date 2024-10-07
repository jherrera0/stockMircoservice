package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Brand;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IBrandPersistencePortTest {

    @Test
    void saveBrand_ShouldSaveBrandSuccessfully() {
        IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
        Brand brand = new Brand();

        brandPersistencePort.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void findByName_ShouldReturnBrand_WhenBrandExists() {
        IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
        Brand expectedBrand = new Brand();
        when(brandPersistencePort.findByName("TestBrand")).thenReturn(Optional.of(expectedBrand));

        Optional<Brand> brand = brandPersistencePort.findByName("TestBrand");

        assertTrue(brand.isPresent());
        assertEquals(expectedBrand, brand.get());
    }

    @Test
    void findByName_ShouldReturnEmpty_WhenBrandDoesNotExist() {
        IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
        when(brandPersistencePort.findByName("NonExistentBrand")).thenReturn(Optional.empty());

        Optional<Brand> brand = brandPersistencePort.findByName("NonExistentBrand");

        assertFalse(brand.isPresent());
    }

    @Test
    void findById_ShouldReturnBrand_WhenBrandExists() {
        IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
        Brand expectedBrand = new Brand();
        when(brandPersistencePort.findById(1L)).thenReturn(Optional.of(expectedBrand));

        Optional<Brand> brand = brandPersistencePort.findById(1L);

        assertTrue(brand.isPresent());
        assertEquals(expectedBrand, brand.get());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenBrandDoesNotExist() {
        IBrandPersistencePort brandPersistencePort = mock(IBrandPersistencePort.class);
        when(brandPersistencePort.findById(999L)).thenReturn(Optional.empty());

        Optional<Brand> brand = brandPersistencePort.findById(999L);

        assertFalse(brand.isPresent());
    }
}