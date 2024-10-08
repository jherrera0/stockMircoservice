package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import bootcamp.stockmircoservice.infrastructure.exception.article.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRequestTest {
      private ArticleRequest articleRequest;
    @Test
    void constructor_ShouldThrowException_WhenDescriptionIsNull() {
        assertThrows(ArticleDescriptionEmptyException.class, () -> articleRequest = new ArticleRequest(1L, "TestName", null, 1L, List.of(1L, 2L, 3L), new BigDecimal("19.99"), 100L));
    }

    @Test
    void constructor_ShouldThrowException_WhenCategoriesIdIsEmpty() {
        assertThrows(ArticleCategoriesIdEmptyException.class, () ->articleRequest = new ArticleRequest(1L, "TestName", "TestDescription", 1L, List.of(), new BigDecimal("19.99"), 100L));
    }

    @Test
    void constructor_ShouldThrowException_WhenStockIsNegative() {
        assertThrows(ArticleIllegalStockValueException.class, () -> articleRequest =new ArticleRequest(1L, "TestName", "TestDescription", 1L, List.of(1L, 2L, 3L), new BigDecimal("19.99"), -100L));
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsEmpty() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleDescriptionEmptyException.class, () -> articleRequest.setDescription(""));
    }

    @Test
    void setBrandId_ShouldThrowException_WhenBrandIdIsNegative() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleBrandIdErrorException.class, () -> articleRequest.setBrandId(-1L));
    }

    @Test
    void setCategoriesId_ShouldThrowException_WhenCategoriesIdIsNull() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleCategoriesIdEmptyException.class, () -> articleRequest.setCategoriesId(null));
    }

    @Test
    void setStock_ShouldThrowException_WhenStockIsNull() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleIllegalStockValueException.class, () -> articleRequest.setStock(null));
    }
    @Test
    void constructor_ShouldThrowException_WhenNameIsEmpty() {
        assertThrows(ArticleNameEmptyException.class, () -> articleRequest = new ArticleRequest(1L, "", "TestDescription", 1L, List.of(1L, 2L, 3L), new BigDecimal("19.99"), 100L));
    }

    @Test
    void setName_ShouldThrowException_WhenNameIsNull() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleNameEmptyException.class, () -> articleRequest.setName(null));
    }

    @Test
    void setDescription_ShouldThrowException_WhenDescriptionIsNull() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleDescriptionEmptyException.class, () -> articleRequest.setDescription(null));
    }

    @Test
    void setCategoriesId_ShouldThrowException_WhenCategoriesIdIsEmpty() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleCategoriesIdEmptyException.class, () -> articleRequest.setCategoriesId(List.of()));
    }

    @Test
    void setStock_ShouldThrowException_WhenStockIsNegative() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticleIllegalStockValueException.class, () -> articleRequest.setStock(-1L));
    }
    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        articleRequest = new ArticleRequest(1L, "TestName", "TestDescription", 1L, List.of(1L, 2L, 3L), new BigDecimal("19.99"), 100L);

        assertEquals(1L, articleRequest.getId());
        assertEquals("TestName", articleRequest.getName());
        assertEquals("TestDescription", articleRequest.getDescription());
        assertEquals(1L, articleRequest.getBrandId());
        assertEquals(List.of(1L, 2L, 3L), articleRequest.getCategoriesId());
        assertEquals(new BigDecimal("19.99"), articleRequest.getPrice());
        assertEquals(100L, articleRequest.getStock());
    }

    @Test
    void setPrice_ShouldThrowException_WhenPriceIsNull() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticlePriceNullException.class, () -> articleRequest.setPrice(null));
    }

    @Test
    void setPrice_ShouldUpdatePrice() {
        articleRequest = new ArticleRequest();
        articleRequest.setPrice(new BigDecimal("29.99"));

        assertEquals(new BigDecimal("29.99"), articleRequest.getPrice());
    }

    @Test
    void setBrandId_ShouldUpdateBrandId() {
        articleRequest = new ArticleRequest();
        articleRequest.setBrandId(2L);

        assertEquals(2L, articleRequest.getBrandId());
    }

    @Test
    void setCategoriesId_ShouldUpdateCategoriesId() {
        articleRequest = new ArticleRequest();
        articleRequest.setCategoriesId(List.of(4L, 5L, 6L));

        assertEquals(List.of(4L, 5L, 6L), articleRequest.getCategoriesId());
    }

    @Test
    void setStock_ShouldUpdateStock() {
        articleRequest = new ArticleRequest();
        articleRequest.setStock(200L);

        assertEquals(200L, articleRequest.getStock());
    }
    @Test
    void setPrice_ShouldThrowException_WhenPriceIsNegative() {
        articleRequest = new ArticleRequest();
        assertThrows(ArticlePriceNegativeException.class, () -> articleRequest.setPrice(new BigDecimal("-1.00")));
    }

    @Test
    void setPrice_ShouldUpdatePrice_WhenPriceIsPositive() {
        articleRequest = new ArticleRequest();
        articleRequest.setPrice(new BigDecimal("19.99"));
        assertEquals(new BigDecimal("19.99"), articleRequest.getPrice());
    }

    @Test
    void constructor_ShouldThrowException_WhenPriceIsNull() {
        assertThrows(ArticlePriceNullException.class, () -> articleRequest = new ArticleRequest(1L, "TestName", "TestDescription", 1L, List.of(1L, 2L, 3L), null, 100L));
    }

    @Test
    void constructor_ShouldThrowException_WhenPriceIsNegative() {
        assertThrows(ArticlePriceNegativeException.class, () -> articleRequest = new ArticleRequest(1L, "TestName", "TestDescription", 1L, List.of(1L, 2L, 3L), new BigDecimal("-1.00"), 100L));
    }
}