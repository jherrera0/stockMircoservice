package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandResponseTest {
    @Test
    void shouldCreateBrandResponseWithValidData() {
        BrandResponse response = new BrandResponse();
        response.setId(1L);
        response.setName("Test Brand");

        assertEquals(1L, response.getId());
        assertEquals("Test Brand", response.getName());
    }

    @Test
    void shouldHandleNullName() {
        BrandResponse response = new BrandResponse();
        response.setId(1L);
        response.setName(null);

        assertEquals(1L, response.getId());
        assertNull(response.getName());
    }

    @Test
    void shouldHandleEmptyId() {
        BrandResponse response = new BrandResponse();
        response.setId(0L);
        response.setName("Test Brand");

        assertEquals(0L, response.getId());
        assertEquals("Test Brand", response.getName());
    }
    @Test
    void shouldCreateBrandResponseWithAllFields() {
        BrandResponse response = new BrandResponse(1L, "Test Brand", "Test Description");

        assertEquals(1L, response.getId());
        assertEquals("Test Brand", response.getName());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void shouldHandleNullDescription() {
        BrandResponse response = new BrandResponse(1L, "Test Brand", null);

        assertEquals(1L, response.getId());
        assertEquals("Test Brand", response.getName());
        assertNull(response.getDescription());
    }

    @Test
    void shouldHandleEmptyName() {
        BrandResponse response = new BrandResponse(1L, "", "Test Description");

        assertEquals(1L, response.getId());
        assertEquals("", response.getName());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void shouldHandleEmptyDescription() {
        BrandResponse response = new BrandResponse(1L, "Test Brand", "");

        assertEquals(1L, response.getId());
        assertEquals("Test Brand", response.getName());
        assertEquals("", response.getDescription());
    }
}