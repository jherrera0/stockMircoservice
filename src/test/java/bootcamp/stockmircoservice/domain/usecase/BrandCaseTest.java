package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
}