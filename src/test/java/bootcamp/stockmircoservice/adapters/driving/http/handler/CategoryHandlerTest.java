package bootcamp.stockmircoservice.adapters.driving.http.handler;


import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.CategoryResponseMapper;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryPageInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategorySizeInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategorySortDirectionEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategorySortDirectionInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryHandlerTest {

    @Mock
    private CategoryResponseMapper categoryResponseMapper;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_ShouldReturnEmptyList_WhenNoCategoriesExist() {
        when(categoryServicePort.getAllCategories(0, 10, "asc")).thenReturn(Collections.emptyList());
        when(categoryResponseMapper.toResponseList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<CategoryResponse> result = categoryHandler.getAllCategories(0, 10, "asc");

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenPageIsNull() {
        assertThrows(CategoryPageInvalidException.class, () -> categoryHandler.getAllCategories(null, 10, "asc"));
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenPageIsNegative() {
        assertThrows(CategoryPageInvalidException.class, () -> categoryHandler.getAllCategories(-1, 10, "asc"));
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenSizeIsNull() {
        assertThrows(CategorySizeInvalidException.class, () -> categoryHandler.getAllCategories(0, null, "asc"));
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenSizeIsNegative() {
        assertThrows(CategorySizeInvalidException.class, () -> categoryHandler.getAllCategories(0, -1, "asc"));
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenSortDirectionIsNull() {
        assertThrows(CategorySortDirectionEmptyException.class, () -> categoryHandler.getAllCategories(0, 10, null));
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenSortDirectionIsEmpty() {
        assertThrows(CategorySortDirectionEmptyException.class, () -> categoryHandler.getAllCategories(0, 10, ""));
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenSortDirectionIsInvalid() {
        assertThrows(CategorySortDirectionInvalidException.class, () -> categoryHandler.getAllCategories(0, 10, "invalid"));
    }

    @Test
    void getAllCategories_ShouldReturnCategories_WhenValidParameters() {
        List<Category> categories = Collections.singletonList(new Category());
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryServicePort.getAllCategories(0, 10, "asc")).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        List<CategoryResponse> result = categoryHandler.getAllCategories(0, 10, "asc");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

}