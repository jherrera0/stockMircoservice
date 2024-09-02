package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNameEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNullFieldException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRequestTest {
    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("TestCategory");

        assertEquals("TestCategory", categoryRequest.getName());
    }

    @Test
    void constructor_ShouldThrowException_WhenNameIsNull() {
        assertThrows(CategoryNullFieldException.class, () -> new CategoryRequest(null));
    }

    @Test
    void setName_ShouldUpdateName() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("UpdatedCategory");

        assertEquals("UpdatedCategory", categoryRequest.getName());
    }

    @Test
    void setName_ShouldThrowException_WhenNameIsEmpty() {
        CategoryRequest categoryRequest = new CategoryRequest();
        assertThrows(CategoryNameEmptyException.class, () -> categoryRequest.setName(""));
    }

    @Test
    void constructor_ShouldThrowException_WhenDescriptionIsNull() {
        assertThrows(CategoryNameEmptyException.class, () -> new CategoryRequest("TestCategory", null));
    }

    @Test
    void constructor_ShouldThrowException_WhenDescriptionIsEmpty() {
        assertThrows(CategoryNameEmptyException.class, () -> new CategoryRequest("TestCategory", ""));
    }

    @Test
    void setDescription_ShouldUpdateDescription() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setDescription("UpdatedDescription");

        assertEquals("UpdatedDescription", categoryRequest.getDescription());
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsNull() {
        CategoryRequest categoryRequest = new CategoryRequest();
        assertThrows(CategoryNameEmptyException.class, () -> categoryRequest.setDescription(null));
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsEmpty() {
        CategoryRequest categoryRequest = new CategoryRequest();
        assertThrows(CategoryNameEmptyException.class, () -> categoryRequest.setDescription(""));
    }

    @Test
    void setName_ShouldThrowException_WhenNameIsOversized() {
        String oversizedName = "A".repeat(ConstValues.CATEGORY_NAME_LENGTH_MAX + 1);
        CategoryRequest categoryRequest = new CategoryRequest();
        assertThrows(CategoryNameEmptyException.class, () -> categoryRequest.setName(oversizedName));
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsOversized() {
        String oversizedDescription = "A".repeat(ConstValues.CATEGORY_DESCRIPTION_LENGTH_MAX + 1);
        CategoryRequest categoryRequest = new CategoryRequest();
        assertThrows(CategoryNameEmptyException.class, () -> categoryRequest.setDescription(oversizedDescription));
    }

}