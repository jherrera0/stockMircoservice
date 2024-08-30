package bootcamp.stockmircoservice.adapters.driving.http.handler;


import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.CategoryRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.CategoryResponseMapper;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
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

}