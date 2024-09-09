package bootcamp.stockmircoservice.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleToPrintTest {
    @Test
    void getId_ShouldReturnId() {
        ArticleToPrint article = new ArticleToPrint();
        article.setId(1L);

        assertEquals(1L, article.getId());
    }

    @Test
    void getName_ShouldReturnName() {
        ArticleToPrint article = new ArticleToPrint();
        article.setName("TestName");

        assertEquals("TestName", article.getName());
    }

    @Test
    void getDescription_ShouldReturnDescription() {
        ArticleToPrint article = new ArticleToPrint();
        article.setDescription("TestDescription");

        assertEquals("TestDescription", article.getDescription());
    }

    @Test
    void getStock_ShouldReturnStock() {
        ArticleToPrint article = new ArticleToPrint();
        article.setStock(100L);

        assertEquals(100L, article.getStock());
    }

    @Test
    void getPrice_ShouldReturnPrice() {
        ArticleToPrint article = new ArticleToPrint();
        article.setPrice(new BigDecimal("19.99"));

        assertEquals(new BigDecimal("19.99"), article.getPrice());
    }

    @Test
    void getBrand_ShouldReturnBrand() {
        ArticleToPrint article = new ArticleToPrint();
        Brand brand = new Brand();
        article.setBrand(brand);

        assertEquals(brand, article.getBrand());
    }

    @Test
    void getCategories_ShouldReturnCategories() {
        ArticleToPrint article = new ArticleToPrint();
        List<Category> categories = List.of(new Category(), new Category());
        article.setCategories(categories);

        assertEquals(categories, article.getCategories());
    }

    @Test
    void getId_ShouldReturnNull_WhenIdIsNotSet() {
        ArticleToPrint article = new ArticleToPrint();
        assertNull(article.getId());
    }

    @Test
    void getName_ShouldReturnNull_WhenNameIsNotSet() {
        ArticleToPrint article = new ArticleToPrint();
        assertNull(article.getName());
    }

    @Test
    void getDescription_ShouldReturnNull_WhenDescriptionIsNotSet() {
        ArticleToPrint article = new ArticleToPrint();
        assertNull(article.getDescription());
    }

    @Test
    void getStock_ShouldReturnNull_WhenStockIsNotSet() {
        ArticleToPrint article = new ArticleToPrint();
        assertNull(article.getStock());
    }

    @Test
    void getPrice_ShouldReturnNull_WhenPriceIsNotSet() {
        ArticleToPrint article = new ArticleToPrint();
        assertNull(article.getPrice());
    }

    @Test
    void getBrand_ShouldReturnNull_WhenBrandIsNotSet() {
        ArticleToPrint article = new ArticleToPrint();
        assertNull(article.getBrand());
    }

    @Test
    void getCategories_ShouldReturnNull_WhenCategoriesAreNotSet() {
        ArticleToPrint article = new ArticleToPrint();
        assertNull(article.getCategories());
    }

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        Brand brand = new Brand();
        List<Category> categories = List.of(new Category(), new Category());
        ArticleToPrint article = new ArticleToPrint();
        article.setId(1L);
        article.setName("TestName");
        article.setDescription("TestDescription");
        article.setStock(100L);
        article.setPrice(new BigDecimal("19.99"));
        article.setBrand(brand);
        article.setCategories(categories);

        assertEquals(1L, article.getId());
        assertEquals("TestName", article.getName());
        assertEquals("TestDescription", article.getDescription());
        assertEquals(100L, article.getStock());
        assertEquals(new BigDecimal("19.99"), article.getPrice());
        assertEquals(brand, article.getBrand());
        assertEquals(categories, article.getCategories());
    }

}