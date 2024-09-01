package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryResponseTest {
    @Test
    void shouldCreateCategoryResponseWithValidData() {
        CategoryResponse response = new CategoryResponse();
        response.setId(1L);
        response.setName("Test Category");

        assertEquals(1L, response.getId());
        assertEquals("Test Category", response.getName());
    }

    @Test
    void shouldHandleNullName() {
        CategoryResponse response = new CategoryResponse();
        response.setId(1L);
        response.setName(null);

        assertEquals(1L, response.getId());
        assertNull(response.getName());
    }

    @Test
    void shouldHandleEmptyId() {
        CategoryResponse response = new CategoryResponse();
        response.setId(0L);
        response.setName("Test Category");

        assertEquals(0L, response.getId());
        assertEquals("Test Category", response.getName());
    }

}