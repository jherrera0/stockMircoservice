package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.category.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private CategoryCase categoryCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryCase = new CategoryCase(categoryPersistencePort);
    }

    @Test
    void saveCategory_withValidCategory_savesSuccessfully() {
        Category category = new Category();
        category.setName("Valid Category");
        category.setDescription("Valid Description");

        when(categoryPersistencePort.findByName("Valid Category")).thenReturn(Optional.empty());

        categoryCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void saveCategory_withEmptyName_throwsCategoryNameEmptyException() {
        Category category = new Category();
        category.setName("");
        category.setDescription("Valid Description");

        assertThrows(CategoryNameEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_withBlankName_throwsCategoryNameEmptyException() {
        Category category = new Category();
        category.setName("   ");
        category.setDescription("Valid Description");

        assertThrows(CategoryNameEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_withOversizeName_throwsCategoryOversizeNameException() {
        Category category = new Category();
        category.setName("A".repeat(Category.MAX_NAME_LENGTH + 1));
        category.setDescription("Valid Description");

        assertThrows(CategoryOversizeNameException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_withEmptyDescription_throwsCategoryDescriptionEmptyException() {
        Category category = new Category();
        category.setName("Valid Category");
        category.setDescription("");

        assertThrows(CategoryDescriptionEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_withBlankDescription_throwsCategoryDescriptionEmptyException() {
        Category category = new Category();
        category.setName("Valid Category");
        category.setDescription("   ");

        assertThrows(CategoryDescriptionEmptyException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_withOversizeDescription_throwsCategoryOversizeDescriptionException() {
        Category category = new Category();
        category.setName("Valid Category");
        category.setDescription("A".repeat(Category.MAX_DESCRIPTION_LENGTH + 1));

        assertThrows(CategoryOversizeDescriptionException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void saveCategory_withExistingName_throwsCategoryAlreadyExistsException() {
        Category category = new Category();
        category.setName("Existing Category");
        category.setDescription("Valid Description");

        when(categoryPersistencePort.findByName("Existing Category")).thenReturn(Optional.of(new Category()));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryCase.saveCategory(category));
    }

    @Test
    void getAllCategories_withValidParameters_returnsCategories() {
        categoryCase.getAllCategories(0, 10, "asc");

        verify(categoryPersistencePort, times(1)).getAllCategories(0, 10, "asc");
    }

    @Test
    void getAllCategories_withNullPage_throwsCategoryPageInvalidException() {
        assertThrows(CategoryPageInvalidException.class, () -> categoryCase.getAllCategories(null, 10, "asc"));
    }

    @Test
    void getAllCategories_withNegativePage_throwsCategoryPageInvalidException() {
        assertThrows(CategoryPageInvalidException.class, () -> categoryCase.getAllCategories(-1, 10, "asc"));
    }

    @Test
    void getAllCategories_withNullSize_throwsCategorySizeInvalidException() {
        assertThrows(CategorySizeInvalidException.class, () -> categoryCase.getAllCategories(0, null, "asc"));
    }

    @Test
    void getAllCategories_withNegativeSize_throwsCategorySizeInvalidException() {
        assertThrows(CategorySizeInvalidException.class, () -> categoryCase.getAllCategories(0, -1, "asc"));
    }

    @Test
    void getAllCategories_withNullSortDirection_throwsCategorySortDirectionEmptyException() {
        assertThrows(CategorySortDirectionEmptyException.class, () -> categoryCase.getAllCategories(0, 10, null));
    }

    @Test
    void getAllCategories_withInvalidSortDirection_throwsCategorySortDirectionInvalidException() {
        assertThrows(CategorySortDirectionInvalidException.class, () -> categoryCase.getAllCategories(0, 10, "invalid"));
    }

    @Test
    void findByArticleId_withExistingArticleId_returnsCategories() {
        categoryCase.findByArticleId(1L);

        verify(categoryPersistencePort, times(1)).findByArticleId(1L);
    }

    @Test
    void findByArticleId_withNonExistingArticleId_returnsEmptyList() {
        when(categoryPersistencePort.findByArticleId(1L)).thenReturn(Collections.emptyList());

        List<Category> result = categoryCase.findByArticleId(1L);

        assertTrue(result.isEmpty());
    }
  
}