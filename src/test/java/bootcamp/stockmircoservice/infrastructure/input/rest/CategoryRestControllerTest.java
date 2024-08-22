package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.ICategoryHandler;
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
        doNothing().when(categoryHandler).saveCategory(categoryRequest);

        ResponseEntity<Void> response = categoryRestController.saveCategory(categoryRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getCategory_ShouldReturnCategories() {
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryHandler.getAllCategories(0, 10, "asc")).thenReturn(categoryResponses);

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategory(0, 10, "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getCategory_ShouldReturnCategories_WhenSortDirectionIsNull() {
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryHandler.getAllCategories(0, 10, null)).thenReturn(categoryResponses);

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategory(0, 10, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getCategory_ShouldReturnCategories_WhenSortDirectionIsEmpty() {
        List<CategoryResponse> categoryResponses = Collections.singletonList(new CategoryResponse());
        when(categoryHandler.getAllCategories(0, 10, "")).thenReturn(categoryResponses);

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategory(0, 10, "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void saveCategory_ShouldHandleException() {
        CategoryRequest categoryRequest = new CategoryRequest();
        doThrow(new RuntimeException("Error")).when(categoryHandler).saveCategory(categoryRequest);

        ResponseEntity<Void> response = categoryRestController.saveCategory(categoryRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getCategory_ShouldHandleException() {
        when(categoryHandler.getAllCategories(0, 10, "asc")).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategory(0, 10, "asc");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}