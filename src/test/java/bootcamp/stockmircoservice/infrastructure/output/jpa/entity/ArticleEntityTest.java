package bootcamp.stockmircoservice.infrastructure.output.jpa.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ArticleEntityTest {
    @Test
    void articleEntity_ShouldHaveCorrectId_WhenSet() {
        ArticleEntity articleEntity = new ArticleEntity();
        Long expectedId = 1L;
        articleEntity.setId(expectedId);

        assertEquals(expectedId, articleEntity.getId());
    }

    @Test
    void articleEntity_ShouldHaveCorrectName_WhenSet() {
        ArticleEntity articleEntity = new ArticleEntity();
        String expectedName = "Test Article";
        articleEntity.setName(expectedName);

        assertEquals(expectedName, articleEntity.getName());
    }

    @Test
    void articleEntity_ShouldHaveCorrectDescription_WhenSet() {
        ArticleEntity articleEntity = new ArticleEntity();
        String expectedDescription = "Test Description";
        articleEntity.setDescription(expectedDescription);

        assertEquals(expectedDescription, articleEntity.getDescription());
    }

    @Test
    void articleEntity_ShouldHaveCorrectPrice_WhenSet() {
        ArticleEntity articleEntity = new ArticleEntity();
        BigDecimal expectedPrice = BigDecimal.valueOf(99.99);
        articleEntity.setPrice(expectedPrice);

        assertEquals(expectedPrice, articleEntity.getPrice());
    }

    @Test
    void articleEntity_ShouldHaveCorrectStock_WhenSet() {
        ArticleEntity articleEntity = new ArticleEntity();
        Long expectedStock = 100L;
        articleEntity.setStock(expectedStock);

        assertEquals(expectedStock, articleEntity.getStock());
    }

    @Test
    void articleEntity_ShouldReturnFalse_WhenEqualsDifferentObject() {
        ArticleEntity articleEntity1 = new ArticleEntity();
        articleEntity1.setId(1L);
        articleEntity1.setName("Test Article");

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setId(2L);
        articleEntity2.setName("Another Article");

        assertFalse(articleEntity1.equals(articleEntity2));
    }

}