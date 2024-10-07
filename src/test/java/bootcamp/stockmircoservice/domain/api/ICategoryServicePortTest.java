package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Category;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ICategoryServicePortTest {

    @Test
    void saveCategory_ShouldSaveCategorySuccessfully() {
        ICategoryServicePort categoryServicePort = mock(ICategoryServicePort.class);
        Category category = new Category();

        categoryServicePort.saveCategory(category);

        verify(categoryServicePort, times(1)).saveCategory(category);
    }

}