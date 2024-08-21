package bootcamp.stockMircoservice.adapters.driving.http.handler;


import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryRequest;
import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryResponse;
import bootcamp.stockMircoservice.adapters.driving.http.mapper.CategoryRequestMapper;
import bootcamp.stockMircoservice.adapters.driving.http.mapper.CategoryResponseMapper;
import bootcamp.stockMircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockMircoservice.domain.model.Category;
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
    private CategoryRequestMapper categoryRequestMapper;

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
    void saveCategory_ShouldCallServicePort() {
        CategoryRequest categoryRequest = new CategoryRequest();
        Category category = new Category();
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        doNothing().when(categoryServicePort).saveCategory(category);

        categoryHandler.saveCategory(categoryRequest);

        verify(categoryServicePort, times(1)).saveCategory(category);
    }
    @Test
    void getAllCategories_ShouldReturnCategoryResponses() {
        List<Category> categories = Collections.singletonList(new Category());
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryServicePort.getAllCategories(0, 10, "asc")).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        List<CategoryResponse> result = categoryHandler.getAllCategories(0, 10, "asc");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
    @Test
    void getAllCategories_ShouldReturnCategoryResponses_WhenSortDirectionIsNull() {
        List<Category> categories = Collections.singletonList(new Category());
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryServicePort.getAllCategories(0, 10, null)).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        List<CategoryResponse> result = categoryHandler.getAllCategories(0, 10, null);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
    @Test
    void getAllCategories_ShouldReturnCategoryResponses_WhenSortDirectionIsEmpty() {
        List<Category> categories = Collections.singletonList(new Category());
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryServicePort.getAllCategories(0, 10, "")).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        List<CategoryResponse> result = categoryHandler.getAllCategories(0, 10, "");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}