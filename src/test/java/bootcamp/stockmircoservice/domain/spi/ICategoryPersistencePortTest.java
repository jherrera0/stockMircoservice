package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Category;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ICategoryPersistencePortTest {

    @Test
    void saveCategory_ShouldSaveCategorySuccessfully() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        Category category = new Category();

        categoryPersistencePort.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void getAllCategories_ShouldReturnEmptyList_WhenNoCategoriesExist() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        when(categoryPersistencePort.getAllCategories(0, 10, "asc")).thenReturn(Collections.emptyList());

        List<Category> categories = categoryPersistencePort.getAllCategories(0, 10, "asc");

        assertTrue(categories.isEmpty());
    }

    @Test
    void getAllCategories_ShouldReturnCategoriesList_WhenCategoriesExist() {
        List<Category> expectedCategories = List.of(new Category());
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        when(categoryPersistencePort.getAllCategories(0, 10, "asc")).thenReturn(expectedCategories);

        List<Category> categories = categoryPersistencePort.getAllCategories(0, 10, "asc");

        assertEquals(expectedCategories, categories);
    }

    @Test
    void findByName_ShouldReturnCategory_WhenCategoryExists() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        Category expectedCategory = new Category();
        when(categoryPersistencePort.findByName("TestCategory")).thenReturn(Optional.of(expectedCategory));

        Optional<Category> category = categoryPersistencePort.findByName("TestCategory");

        assertTrue(category.isPresent());
        assertEquals(expectedCategory, category.get());
    }

    @Test
    void findByName_ShouldReturnEmpty_WhenCategoryDoesNotExist() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        when(categoryPersistencePort.findByName("NonExistentCategory")).thenReturn(Optional.empty());

        Optional<Category> category = categoryPersistencePort.findByName("NonExistentCategory");

        assertFalse(category.isPresent());
    }

    @Test
    void findById_ShouldReturnCategory_WhenCategoryExists() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        Category expectedCategory = new Category();
        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(expectedCategory));

        Optional<Category> category = categoryPersistencePort.findById(1L);

        assertTrue(category.isPresent());
        assertEquals(expectedCategory, category.get());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenCategoryDoesNotExist() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        when(categoryPersistencePort.findById(999L)).thenReturn(Optional.empty());

        Optional<Category> category = categoryPersistencePort.findById(999L);

        assertFalse(category.isPresent());
    }
}