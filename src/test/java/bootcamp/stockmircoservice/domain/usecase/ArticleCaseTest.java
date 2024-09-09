package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.CategoriesSizeException;
import bootcamp.stockmircoservice.infrastructure.exception.article.DuplicateCategoriesException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandNotFoundException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ArticleCaseTest {

    private IArticlePersistencePort articlePersistencePort;
    private IBrandPersistencePort branchPersistencePort;
    private ICategoryPersistencePort categoryPersistencePort;
    private ArticleCase articleCase;

    @BeforeEach
    void setUp() {
        articlePersistencePort = mock(IArticlePersistencePort.class);
        branchPersistencePort = mock(IBrandPersistencePort.class);
        categoryPersistencePort = mock(ICategoryPersistencePort.class);
        articleCase = new ArticleCase(articlePersistencePort, branchPersistencePort, categoryPersistencePort);
    }
    @Test
    void saveArticle_withNonExistentBrand_throwsBrandNotFoundException() {
        Article article = new Article();
        article.setName("Test");
        article.setDescription("Test");
        article.setCategoriesId(Arrays.asList(1L, 2L));
        article.setBrandId(1L);
        article.setStock(1L);
        article.setPrice(BigDecimal.valueOf(100));

        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(new Category()));
        when(categoryPersistencePort.findById(2L)).thenReturn(Optional.of(new Category()));
        when(branchPersistencePort.findById(1L)).thenThrow(new BrandNotFoundException());

        assertThrows(BrandNotFoundException.class, () -> articleCase.saveArticle(article));
    }

    @Test
    void saveArticle_withNonExistentCategory_throwsCategoryNotExistException() {
        Article article = new Article();
        article.setName("Test");
        article.setDescription("Test");
        article.setCategoriesId(Arrays.asList(1L, 2L));
        article.setBrandId(1L);
        article.setStock(1L);
        article.setPrice(BigDecimal.valueOf(100));

        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(new Category()));
        when(categoryPersistencePort.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotExistException.class, () -> articleCase.saveArticle(article));
    }
    @Test
    void saveArticle_withDuplicateCategories_throwsDuplicateCategoriesException() {
        Article article = new Article();
        article.setCategoriesId(Arrays.asList(1L, 1L));

        assertThrows(DuplicateCategoriesException.class, () -> articleCase.saveArticle(article));
    }

    @Test
    void saveArticle_withEmptyCategories_throwsCategoriesSizeException() {
        Article article = new Article();
        article.setCategoriesId(Collections.emptyList());

        assertThrows(CategoriesSizeException.class, () -> articleCase.saveArticle(article));
    }

    @Test
    void saveArticle_withMoreThanThreeCategories_throwsCategoriesSizeException() {
        Article article = new Article();
        article.setCategoriesId(Arrays.asList(1L, 2L, 3L, 4L));

        assertThrows(CategoriesSizeException.class, () -> articleCase.saveArticle(article));
    }

    @Test
    void getAllArticles_withValidParameters_returnsArticles() {
        articleCase.getAllArticles(0, 10, "asc", "name");

        verify(articlePersistencePort, times(1)).getAllArticles(0, 10, "asc", "name");
    }
}