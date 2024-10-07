package bootcamp.stockmircoservice.infrastructure.until;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void validationSaveArticle_withValidArticle_doesNotThrowException() {
        Article article = new Article();
        article.setBrandId(1L);
        article.setPrice(BigDecimal.valueOf(100));
        article.setName("Valid Name");
        article.setDescription("Valid Description");
        article.setStock(10L);
        article.setCategoriesId(Arrays.asList(1L, 2L));

        assertDoesNotThrow(() -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withNullArticle_throwsArticleRequestNullException() {
        assertThrows(ArticleRequestNullException.class, () -> Validation.validationSaveArticle(null));
    }

    @Test
    void validationSaveArticle_withNullBrandId_throwsArticleBrandIdErrorException() {
        Article article = new Article();
        article.setBrandId(null);

        assertThrows(ArticleBrandIdErrorException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withNegativeBrandId_throwsArticleBrandIdErrorException() {
        Article article = new Article();
        article.setBrandId(-1L);

        assertThrows(ArticleBrandIdErrorException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withNullPrice_throwsArticlePriceNullException() {
        Article article = new Article();
        article.setName("Test");
        article.setDescription("Test");
        article.setCategoriesId(Arrays.asList(1L, 2L));
        article.setBrandId(1L);
        article.setStock(1L);
        article.setPrice(null);


        assertThrows(ArticlePriceNullException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withNegativePrice_throwsArticlePriceNegativeException() {
        Article article = new Article();
        article.setName("Test");
        article.setDescription("Test");
        article.setCategoriesId(Arrays.asList(1L, 2L));
        article.setBrandId(1L);
        article.setStock(1L);
        article.setPrice(BigDecimal.valueOf(-100));


        assertThrows(ArticlePriceNegativeException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withEmptyName_throwsArticleNameEmptyException() {
        Article article = new Article();
        article.setName("");
        article.setDescription("Test");
        article.setCategoriesId(Arrays.asList(1L, 2L));
        article.setBrandId(1L);
        article.setStock(1L);
        article.setPrice(BigDecimal.valueOf(100));


        assertThrows(ArticleNameEmptyException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withNullDescription_throwsArticleDescriptionEmptyException() {
        Article article = new Article();
        article.setName("Test");
        article.setDescription("");
        article.setCategoriesId(Arrays.asList(1L, 2L));
        article.setBrandId(1L);
        article.setStock(1L);
        article.setPrice(BigDecimal.valueOf(100));


        assertThrows(ArticleDescriptionEmptyException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withNegativeStock_throwsArticleIllegalStockValueException() {
        Article article = new Article();
        article.setName("Test");
        article.setDescription("Test");
        article.setCategoriesId(Arrays.asList(1L, 2L));
        article.setBrandId(1L);
        article.setStock(-1L);
        article.setPrice(BigDecimal.valueOf(100));


        assertThrows(ArticleIllegalStockValueException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationSaveArticle_withEmptyCategoriesId_throwsArticleCategoriesIdEmptyException() {
        Article article = new Article();
        article.setName("Test");
        article.setDescription("Test");
        article.setBrandId(1L);
        article.setStock(1L);
        article.setPrice(BigDecimal.valueOf(100));
        article.setCategoriesId(Collections.emptyList());

        assertThrows(ArticleCategoriesIdEmptyException.class, () -> Validation.validationSaveArticle(article));
    }

    @Test
    void validationGetAllArticles_withValidParameters_doesNotThrowException() {
        assertDoesNotThrow(() -> Validation.validationGetAllArticles(0, 10, "asc", "name"));
    }

    @Test
    void validationGetAllArticles_withNullPage_throwsArticlePageEmptyException() {
        assertThrows(ArticlePageEmptyException.class, () -> Validation.validationGetAllArticles(null, 10, "asc", "name"));
    }

    @Test
    void validationGetAllArticles_withNegativePage_throwsArticlePageEmptyException() {
        assertThrows(ArticlePageEmptyException.class, () -> Validation.validationGetAllArticles(-1, 10, "asc", "name"));
    }

    @Test
    void validationGetAllArticles_withNullSize_throwsArticleSizeEmptyException() {
        assertThrows(ArticleSizeEmptyException.class, () -> Validation.validationGetAllArticles(0, null, "asc", "name"));
    }

    @Test
    void validationGetAllArticles_withNegativeSize_throwsArticleSizeEmptyException() {
        assertThrows(ArticleSizeEmptyException.class, () -> Validation.validationGetAllArticles(0, -1, "asc", "name"));
    }

    @Test
    void validationGetAllArticles_withNullSortDirection_throwsArticleSortDirectionEmptyException() {
        assertThrows(ArticleSortDirectionEmptyException.class, () -> Validation.validationGetAllArticles(0, 10, null, "name"));
    }

    @Test
    void validationGetAllArticles_withInvalidSortDirection_throwsArticleSortDirectionInvalidException() {
        assertThrows(ArticleSortDirectionInvalidException.class, () -> Validation.validationGetAllArticles(0, 10, "invalid", "name"));
    }

    @Test
    void validationGetAllArticles_withNullSortBy_throwsArticleSortByEmptyException() {
        assertThrows(ArticleSortByEmptyException.class, () -> Validation.validationGetAllArticles(0, 10, "asc", null));
    }

    @Test
    void validationGetAllArticles_withInvalidSortBy_throwsArticleSortByInvalidException() {
        assertThrows(ArticleSortByInvalidException.class, () -> Validation.validationGetAllArticles(0, 10, "asc", "invalid"));
    }
    @Test
    void validationUpdateArticle_withValidParameters_doesNotThrowException() {
        assertDoesNotThrow(() -> Validation.validationUpdateArticle(1L, 10L));
    }

    @Test
    void validationUpdateArticle_withNullId_throwsArticleIdNullException() {
        assertThrows(ArticleIdNullException.class, () -> Validation.validationUpdateArticle(null, 10L));
    }

    @Test
    void validationUpdateArticle_withNegativeId_throwsArticleIdNullException() {
        assertThrows(ArticleIdNullException.class, () -> Validation.validationUpdateArticle(-1L, 10L));
    }

    @Test
    void validationUpdateArticle_withNullQuantity_throwsArticleQuantityNullException() {
        assertThrows(ArticleQuantityNullException.class, () -> Validation.validationUpdateArticle(1L, null));
    }

    @Test
    void validationUpdateArticle_withNegativeQuantity_throwsArticleQuantityNullException() {
        assertThrows(ArticleQuantityNullException.class, () -> Validation.validationUpdateArticle(1L, -10L));
    }
}