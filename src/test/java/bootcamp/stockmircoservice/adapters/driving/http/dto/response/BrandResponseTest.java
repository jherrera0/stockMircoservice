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
}