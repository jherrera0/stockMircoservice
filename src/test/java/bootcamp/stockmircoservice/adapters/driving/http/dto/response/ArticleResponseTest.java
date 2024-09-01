package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ArticleResponseTest {
    @Test
    void shouldCreateArticleResponseWithValidData() {
        ArticleResponse response = new ArticleResponse();
        response.setId(123L);
        response.setName("Test Article");
        response.setDescription("Test Description");
        response.setBrand(new BrandResponse());
        response.setCategories(Collections.singletonList(new CategoryResponse()));
        response.setPrice(BigDecimal.valueOf(10.0));
        response.setStock(100L);

        assertEquals(123L, response.getId());
        assertEquals("Test Article", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertNotNull(response.getBrand());
        assertNotNull(response.getCategories());
        assertEquals(BigDecimal.valueOf(10.0), response.getPrice());
        assertEquals(100L, response.getStock());
    }

    @Test
    void shouldHandleNullName() {
        ArticleResponse response = new ArticleResponse();
        response.setId(123L);
        response.setName(null);
        response.setDescription("Test Description");
        response.setBrand(new BrandResponse());
        response.setCategories(Collections.singletonList(new CategoryResponse()));
        response.setPrice(BigDecimal.valueOf(10.0));
        response.setStock(100L);

        assertEquals(123L, response.getId());
        assertNull(response.getName());
        assertEquals("Test Description", response.getDescription());
        assertNotNull(response.getBrand());
        assertNotNull(response.getCategories());
        assertEquals(BigDecimal.valueOf(10.0), response.getPrice());
        assertEquals(100L, response.getStock());
    }

    @Test
    void shouldHandleNegativePrice() {
        ArticleResponse response = new ArticleResponse();
        response.setId(123L);
        response.setName("Test Article");
        response.setDescription("Test Description");
        response.setBrand(new BrandResponse());
        response.setCategories(Collections.singletonList(new CategoryResponse()));
        response.setPrice(BigDecimal.valueOf(-10.0));
        response.setStock(100L);

        assertEquals(123L, response.getId());
        assertEquals("Test Article", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertNotNull(response.getBrand());
        assertNotNull(response.getCategories());
        assertEquals(BigDecimal.valueOf(-10.0), response.getPrice());
        assertEquals(100L, response.getStock());
    }

    @Test
    void shouldHandleEmptyId() {
        ArticleResponse response = new ArticleResponse();
        response.setId(0L);
        response.setName("Test Article");
        response.setDescription("Test Description");
        response.setBrand(new BrandResponse());
        response.setCategories(Collections.singletonList(new CategoryResponse()));
        response.setPrice(BigDecimal.valueOf(10.0));
        response.setStock(100L);

        assertEquals(0L, response.getId());
        assertEquals("Test Article", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertNotNull(response.getBrand());
        assertNotNull(response.getCategories());
        assertEquals(BigDecimal.valueOf(10.0), response.getPrice());
        assertEquals(100L, response.getStock());
    }

}