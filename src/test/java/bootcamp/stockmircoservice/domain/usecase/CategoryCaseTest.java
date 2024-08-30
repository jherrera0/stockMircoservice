package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.category.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryCaseTest {
    @Test
    void saveCategory_savesCategorySuccessfully_whenValidCategory() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("Valid Name");
        category.setDescription("Valid Description");

        categoryCase.saveCategory(category);

        verify(categoryPersistencePort).saveCategory(category);
    }

    @Test
    void saveCategory_throwsCategoryNameEmptyException_whenNameIsEmpty() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("");
        category.setDescription("Valid Description");

        assertThrows(CategoryNameEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_throwsCategoryNameEmptyException_whenNameIsBlank() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("   ");
        category.setDescription("Valid Description");

        assertThrows(CategoryNameEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_throwsCategoryOversizeNameException_whenNameExceedsMaxLength() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("A".repeat(Category.MAX_NAME_LENGTH + 1));
        category.setDescription("Valid Description");

        assertThrows(CategoryOversizeNameException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_throwsCategoryDescriptionEmptyException_whenDescriptionIsEmpty() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("Valid Name");
        category.setDescription("");

        assertThrows(CategoryDescriptionEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_throwsCategoryDescriptionEmptyException_whenDescriptionIsBlank() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("Valid Name");
        category.setDescription("   ");

        assertThrows(CategoryDescriptionEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_throwsCategoryOversizeDescriptionException_whenDescriptionExceedsMaxLength() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("Valid Name");
        category.setDescription("A".repeat(Category.MAX_DESCRIPTION_LENGTH + 1));

        assertThrows(CategoryOversizeDescriptionException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_throwsCategoryAlreadyExistsException_whenCategoryNameAlreadyExists() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        Category category = new Category();
        category.setName("Existing Name");
        category.setDescription("Valid Description");

        when(categoryPersistencePort.findByName("Existing Name")).thenReturn(Optional.of(category));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void getAllCategories_returnsCategoriesList_whenValidPageSizeAndSortDirection() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryPersistencePort.getAllCategories(0, 10, "asc")).thenReturn(categories);

        List<Category> result = categoryCase.getAllCategories(0, 10, "asc");

        assertEquals(categories, result);
        verify(categoryPersistencePort).getAllCategories(0, 10, "asc");
    }

    @Test
    void getAllCategories_returnsEmptyList_whenNoCategoriesFound() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        when(categoryPersistencePort.getAllCategories(0, 10, "asc")).thenReturn(Collections.emptyList());

        List<Category> result = categoryCase.getAllCategories(0, 10, "asc");

        assertTrue(result.isEmpty());
        verify(categoryPersistencePort).getAllCategories(0, 10, "asc");
    }
    @Test
    void getAllCategories_throwsCategoryRequestNegativeException_whenPageIsNull() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);

        assertThrows(CategoryRequestNegativeException.class, () -> categoryCase.getAllCategories(null, 10, "asc"));
    }

    @Test
    void getAllCategories_throwsCategoryRequestNegativeException_whenSizeIsNull() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);

        assertThrows(CategoryRequestNegativeException.class, () -> categoryCase.getAllCategories(0, null, "asc"));
    }
    @Test
    void getAllCategories_throwsCategoryRequestNegativeException_whenPageIsNegative() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);

        assertThrows(CategoryRequestNegativeException.class, () -> categoryCase.getAllCategories(-1, 10, "asc"));
    }

    @Test
    void getAllCategories_throwsCategoryRequestNegativeException_whenSizeIsNegative() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);

        assertThrows(CategoryRequestNegativeException.class, () -> categoryCase.getAllCategories(0, -1, "asc"));
    }

    @Test
    void getAllCategories_returnsCategoriesList_whenSortDirectionIsNull() {
        ICategoryPersistencePort categoryPersistencePort = mock(ICategoryPersistencePort.class);
        CategoryCase categoryCase = new CategoryCase(categoryPersistencePort);
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryPersistencePort.getAllCategories(0, 10, null)).thenReturn(categories);

        List<Category> result = categoryCase.getAllCategories(0, 10, null);

        assertEquals(categories, result);
        verify(categoryPersistencePort).getAllCategories(0, 10, null);
    }
}
