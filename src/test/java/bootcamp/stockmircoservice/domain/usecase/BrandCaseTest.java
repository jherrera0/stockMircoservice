package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    private BrandCase brandCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandCase = new BrandCase(brandPersistencePort);
    }


    @Test
    void saveBrand_withValidBrand_savesSuccessfully() {
        Brand brand = new Brand();
        brand.setName("Valid Brand");
        brand.setDescription("Valid Description");

        when(brandPersistencePort.findByName("Valid Brand")).thenReturn(Optional.empty());

        brandCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void saveBrand_withNullBrand_throwsBrandNullFieldException() {
        assertThrows(BrandNullFieldException.class, () -> brandCase.saveBrand(null));
    }

    @Test
    void saveBrand_withNullName_throwsBrandNullFieldException() {
        Brand brand = new Brand();
        brand.setDescription("Valid Description");

        assertThrows(BrandNullFieldException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_withEmptyName_throwsBrandNameEmptyException() {
        Brand brand = new Brand();
        brand.setName("");
        brand.setDescription("Valid Description");

        assertThrows(BrandNameEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_withOversizeName_throwsBrandOversizeNameException() {
        Brand brand = new Brand();
        brand.setName("A".repeat(Brand.MAX_NAME_LENGTH + 1));
        brand.setDescription("Valid Description");

        assertThrows(BrandOversizeNameException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_withNullDescription_throwsBrandNullFieldException() {
        Brand brand = new Brand();
        brand.setName("Valid Brand");

        assertThrows(BrandNullFieldException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_withEmptyDescription_throwsBrandDescriptionEmptyException() {
        Brand brand = new Brand();
        brand.setName("Valid Brand");
        brand.setDescription("");

        assertThrows(BrandDescriptionEmptyException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_withOversizeDescription_throwsBrandOversizeDescriptionException() {
        Brand brand = new Brand();
        brand.setName("Valid Brand");
        brand.setDescription("A".repeat(Brand.MAX_DESCRIPTION_LENGTH + 1));

        assertThrows(BrandOversizeDescriptionException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void saveBrand_withExistingName_throwsBrandAlreadyExistsException() {
        Brand brand = new Brand();
        brand.setName("Existing Brand");
        brand.setDescription("Valid Description");

        when(brandPersistencePort.findByName("Existing Brand")).thenReturn(Optional.of(new Brand()));

        assertThrows(BrandAlreadyExistsException.class, () -> brandCase.saveBrand(brand));
    }

    @Test
    void getAllBrands_withValidParameters_returnsBrands() {
        brandCase.getAllBRands(0, 10, "asc");

        verify(brandPersistencePort, times(1)).getAllBrands(0, 10, "asc");
    }

    @Test
    void getAllBrands_withNullPage_throwsBrandPageInvalidException() {
        assertThrows(BrandPageInvalidException.class, () -> brandCase.getAllBRands(null, 10, "asc"));
    }

    @Test
    void getAllBrands_withNegativePage_throwsBrandPageInvalidException() {
        assertThrows(BrandPageInvalidException.class, () -> brandCase.getAllBRands(-1, 10, "asc"));
    }

    @Test
    void getAllBrands_withNullSize_throwsBrandSizeInvalidException() {
        assertThrows(BrandSizeInvalidException.class, () -> brandCase.getAllBRands(0, null, "asc"));
    }

    @Test
    void getAllBrands_withNegativeSize_throwsBrandSizeInvalidException() {
        assertThrows(BrandSizeInvalidException.class, () -> brandCase.getAllBRands(0, -1, "asc"));
    }

    @Test
    void getAllBrands_withNullSortDirection_throwsBrandSortDirectionEmptyException() {
        assertThrows(BrandSortDirectionEmptyException.class, () -> brandCase.getAllBRands(0, 10, null));
    }

    @Test
    void getAllBrands_withInvalidSortDirection_throwsBrandSortDirectionInvalidException() {
        assertThrows(BrandSortDirectionInvalidException.class, () -> brandCase.getAllBRands(0, 10, "invalid"));
    }

    @Test
    void findById_withExistingId_returnsBrand() {
        Brand brand = new Brand();
        when(brandPersistencePort.findById(1L)).thenReturn(Optional.of(brand));

        Brand result = brandCase.findById(1L);

        assertEquals(brand, result);
    }

    @Test
    void findById_withNonExistingId_throwsBrandNotFoundException() {
        when(brandPersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> brandCase.findById(1L));
    }
  
}