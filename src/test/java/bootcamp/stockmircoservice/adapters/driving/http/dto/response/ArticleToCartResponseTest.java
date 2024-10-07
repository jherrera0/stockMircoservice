package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleToCartResponseTest {
    @Test
    void constructor_withValidParameters_initializesFieldsCorrectly() {
        List<String> categories = List.of("Electronics", "Home");
        ArticleToCartResponse response = new ArticleToCartResponse(1L, 2L, "Product", "Brand", categories, 99.99);
        assertEquals(1L, response.getProductId());
        assertEquals(2L, response.getQuantity());
        assertEquals("Product", response.getProductName());
        assertEquals("Brand", response.getBrandName());
        assertEquals(categories, response.getCategories());
        assertEquals(99.99, response.getPrice());
    }

    @Test
    void constructor_withNoParameters_initializesFieldsToNull() {
        ArticleToCartResponse response = new ArticleToCartResponse();
        assertNull(response.getProductId());
        assertNull(response.getQuantity());
        assertNull(response.getProductName());
        assertNull(response.getBrandName());
        assertNull(response.getCategories());
        assertNull(response.getPrice());
    }

    @Test
    void setProductId_withValidValue_setsProductIdCorrectly() {
        ArticleToCartResponse response = new ArticleToCartResponse();
        response.setProductId(1L);
        assertEquals(1L, response.getProductId());
    }


    @Test
    void setQuantity_withValidValue_setsQuantityCorrectly() {
        ArticleToCartResponse response = new ArticleToCartResponse();
        response.setQuantity(2L);
        assertEquals(2L, response.getQuantity());
    }

    @Test
    void setProductName_withValidValue_setsProductNameCorrectly() {
        ArticleToCartResponse response = new ArticleToCartResponse();
        response.setProductName("Product");
        assertEquals("Product", response.getProductName());
    }


    @Test
    void setBrandName_withValidValue_setsBrandNameCorrectly() {
        ArticleToCartResponse response = new ArticleToCartResponse();
        response.setBrandName("Brand");
        assertEquals("Brand", response.getBrandName());
    }


    @Test
    void setCategories_withNonNullList_setsCategoriesCorrectly() {
        ArticleToCartResponse response = new ArticleToCartResponse();
        List<String> categories = List.of("Electronics", "Home");
        response.setCategories(categories);
        assertEquals(categories, response.getCategories());
    }


    @Test
    void setPrice_withValidValue_setsPriceCorrectly() {
        ArticleToCartResponse response = new ArticleToCartResponse();
        response.setPrice(99.99);
        assertEquals(99.99, response.getPrice());
    }

}