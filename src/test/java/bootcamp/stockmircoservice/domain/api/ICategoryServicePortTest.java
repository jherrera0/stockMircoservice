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

    @Test
    void getAllCategories_ShouldReturnEmptyList_WhenNoCategoriesExist() {
        ICategoryServicePort categoryServicePort = mock(ICategoryServicePort.class);
        when(categoryServicePort.getAllCategories(0, 10, "asc")).thenReturn(Collections.emptyList());

        List<Category> categories = categoryServicePort.getAllCategories(0, 10, "asc");

        assertTrue(categories.isEmpty());
    }

    @Test
    void getAllCategories_ShouldReturnCategoriesList_WhenCategoriesExist() {
        List<Category> expectedCategories = List.of(new Category());
        ICategoryServicePort categoryServicePort = mock(ICategoryServicePort.class);
        when(categoryServicePort.getAllCategories(0, 10, "asc")).thenReturn(expectedCategories);

        List<Category> categories = categoryServicePort.getAllCategories(0, 10, "asc");

        assertEquals(expectedCategories, categories);
    }
}