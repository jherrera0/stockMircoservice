package bootcamp.stockmircoservice.adapters.driving.http.handler;


import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.CategoryRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IPageCustomResponseMapper;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import bootcamp.stockmircoservice.infrastructure.exception.category.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryHandlerTest {

    @Mock
    private IPageCustomResponseMapper pageCustomResponseMapper;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;



    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveCategory_ShouldThrowException_WhenRequestIsNull() {
        assertThrows(CategoryRequestNullException.class, () -> categoryHandler.saveCategory(null));
    }

    @Test
    void saveCategory_ShouldCallServicePort_WhenValidRequest() {
        CategoryRequest categoryRequest = new CategoryRequest();
        Category category = new Category();
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);

        categoryHandler.saveCategory(categoryRequest);

        verify(categoryServicePort, times(1)).saveCategory(category);
    }
    @Test
    void getAllCategories_withValidParameters_returnsPageCustomResponse() {
        PageCustom<Category> pageCustom = new PageCustom<>(1, 10, 1, List.of(new Category()));
        PageCustomResponse<CategoryResponse> pageCustomResponse = new PageCustomResponse<>();
        when(categoryServicePort.getAllCategories(1, 10, "asc")).thenReturn(pageCustom);
        when(pageCustomResponseMapper.toResponsePageOfCategory(pageCustom)).thenReturn(pageCustomResponse);

        PageCustomResponse<CategoryResponse> result = categoryHandler.getAllCategories(1, 10, "asc");

        assertEquals(pageCustomResponse, result);
    }
}