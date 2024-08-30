package bootcamp.stockmircoservice.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    void getId_ShouldReturnId() {
        Article article = new Article();
        article.setId(1L);

        assertEquals(1L, article.getId());
    }

    @Test
    void setId_ShouldSetId() {
        Article article = new Article();
        article.setId(1L);

        assertEquals(1L, article.getId());
    }

    @Test
    void getName_ShouldReturnName() {
        Article article = new Article();
        article.setName("TestName");

        assertEquals("TestName", article.getName());
    }

    @Test
    void setName_ShouldSetName() {
        Article article = new Article();
        article.setName("TestName");

        assertEquals("TestName", article.getName());
    }

    @Test
    void getDescription_ShouldReturnDescription() {
        Article article = new Article();
        article.setDescription("TestDescription");

        assertEquals("TestDescription", article.getDescription());
    }

    @Test
    void setDescription_ShouldSetDescription() {
        Article article = new Article();
        article.setDescription("TestDescription");

        assertEquals("TestDescription", article.getDescription());
    }

    @Test
    void getStock_ShouldReturnStock() {
        Article article = new Article();
        article.setStock(100L);

        assertEquals(100L, article.getStock());
    }

    @Test
    void setStock_ShouldSetStock() {
        Article article = new Article();
        article.setStock(100L);

        assertEquals(100L, article.getStock());
    }

    @Test
    void getPrice_ShouldReturnPrice() {
        Article article = new Article();
        article.setPrice(new BigDecimal("19.99"));

        assertEquals(new BigDecimal("19.99"), article.getPrice());
    }

    @Test
    void setPrice_ShouldSetPrice() {
        Article article = new Article();
        article.setPrice(new BigDecimal("19.99"));

        assertEquals(new BigDecimal("19.99"), article.getPrice());
    }

    @Test
    void getBrandId_ShouldReturnBrandId() {
        Article article = new Article();
        article.setBrandId(1L);

        assertEquals(1L, article.getBrandId());
    }

    @Test
    void setBrandId_ShouldSetBrandId() {
        Article article = new Article();
        article.setBrandId(1L);

        assertEquals(1L, article.getBrandId());
    }

    @Test
    void getCategoriesId_ShouldReturnCategoriesId() {
        Article article = new Article();
        List<Long> categoriesId = List.of(1L, 2L, 3L);
        article.setCategoriesId(categoriesId);

        assertEquals(categoriesId, article.getCategoriesId());
    }

    @Test
    void setCategoriesId_ShouldSetCategoriesId() {
        Article article = new Article();
        List<Long> categoriesId = List.of(1L, 2L, 3L);
        article.setCategoriesId(categoriesId);

        assertEquals(categoriesId, article.getCategoriesId());
    }
}