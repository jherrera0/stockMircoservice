package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.ICategoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryRestControllerTest {

    @Mock
    private ICategoryHandler categoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldReturnCreatedStatus() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Valid Category");

        doNothing().when(categoryHandler).saveCategory(categoryRequest);

        ResponseEntity<Void> response = categoryRestController.saveCategory(categoryRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getCategories_ShouldReturnCategories() {
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryHandler.getAllCategories(0, 10, "asc")).thenReturn(categoryResponses);

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategories(0, 10, "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getCategories_ShouldReturnCategories_WhenSortDirectionIsEmpty() {
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryHandler.getAllCategories(0, 10, "")).thenReturn(categoryResponses);

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategories(0, 10, "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }
}