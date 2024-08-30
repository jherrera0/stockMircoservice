package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.CategoriesSizeException;
import bootcamp.stockmircoservice.infrastructure.exception.article.DuplicateCategoriesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ArticleCaseTest {

    @Test
    void saveArticle_savesArticleSuccessfully_whenValidArticle() {
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
        ArticleCase articleCase = new ArticleCase(articlePersistencePort);
        Article article = new Article();
        article.setCategoriesId((new ArrayList<>(Arrays.asList(1L, 2L, 3L))));

        articleCase.saveArticle(article);

        verify(articlePersistencePort).saveArticle(article);
    }

    @Test
    void saveArticle_throwsDuplicateCategoriesException_whenDuplicateCategories() {
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
        ArticleCase articleCase = new ArticleCase(articlePersistencePort);
        Article article = new Article();
        article.setCategoriesId(new ArrayList<>(Arrays.asList(1L, 1L, 3L)));

        assertThrows(DuplicateCategoriesException.class, () -> articleCase.saveArticle(article));
    }

    @Test
    void saveArticle_throwsCategoriesSizeException_whenCategoriesSizeIsLessThanZero() {
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
        ArticleCase articleCase = new ArticleCase(articlePersistencePort);
        Article article = new Article();
        article.setCategoriesId(Arrays.asList());

        assertThrows(CategoriesSizeException.class, () -> articleCase.saveArticle(article));
    }

    @Test
    void saveArticle_throwsCategoriesSizeException_whenCategoriesSizeIsGreaterThanThree() {
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
        ArticleCase articleCase = new ArticleCase(articlePersistencePort);
        Article article = new Article();
        article.setCategoriesId(new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L)));

        assertThrows(CategoriesSizeException.class, () -> articleCase.saveArticle(article));
    }
}