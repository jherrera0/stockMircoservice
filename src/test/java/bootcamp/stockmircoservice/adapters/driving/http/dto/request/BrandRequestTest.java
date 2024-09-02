package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandNameEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandNullFieldException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandOversizeNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandRequestTest {
    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("TestBrand");

        assertEquals("TestBrand", brandRequest.getName());
    }

    @Test
    void constructor_ShouldThrowException_WhenNameIsNull() {
        assertThrows(BrandNullFieldException.class, () -> new BrandRequest(null));
    }

    @Test
    void setName_ShouldUpdateName() {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("UpdatedBrand");

        assertEquals("UpdatedBrand", brandRequest.getName());
    }

    @Test
    void setName_ShouldThrowException_WhenNameIsEmpty() {
        BrandRequest brandRequest = new BrandRequest();
        assertThrows(BrandNameEmptyException.class, () -> brandRequest.setName(""));
    }

    @Test
    void constructor_ShouldThrowException_WhenDescriptionIsNull() {
        assertThrows(BrandNameEmptyException.class, () -> new BrandRequest("TestBrand", null));
    }

    @Test
    void constructor_ShouldThrowException_WhenDescriptionIsEmpty() {
        assertThrows(BrandNameEmptyException.class, () -> new BrandRequest("TestBrand", ""));
    }

    @Test
    void setDescription_ShouldUpdateDescription() {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setDescription("UpdatedDescription");

        assertEquals("UpdatedDescription", brandRequest.getDescription());
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsNull() {
        BrandRequest brandRequest = new BrandRequest();
        assertThrows(BrandNameEmptyException.class, () -> brandRequest.setDescription(null));
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsEmpty() {
        BrandRequest brandRequest = new BrandRequest();
        assertThrows(BrandNameEmptyException.class, () -> brandRequest.setDescription(""));
    }

    @Test
    void setName_ShouldThrowException_WhenNameIsOversized() {
        BrandRequest brandRequest = new BrandRequest();
        assertThrows(BrandOversizeNameException.class, () -> brandRequest.setName("A".repeat(ConstValues.BRAND_NAME_LENGTH_MAX + 1)));
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsOversized() {
        BrandRequest brandRequest = new BrandRequest();
        assertThrows(BrandOversizeNameException.class, () -> brandRequest.setDescription("A".repeat(ConstValues.BRAND_DESCRIPTION_LENGTH_MAX + 1)));
    }

}