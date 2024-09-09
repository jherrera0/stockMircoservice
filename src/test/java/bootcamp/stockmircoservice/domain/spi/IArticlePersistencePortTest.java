package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IArticlePersistencePortTest {

    @Test
    void saveArticle_ShouldSaveArticleSuccessfully() {
        Article article = new Article();
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);

        articlePersistencePort.saveArticle(article);

        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void getAllArticles_ShouldReturnEmptyList_WhenNoArticlesExist() {
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
        when(articlePersistencePort.getAllArticles(0, 10, "asc", "id")).thenReturn(Collections.emptyList());

        List<ArticleToPrint> articles = articlePersistencePort.getAllArticles(0, 10, "asc", "id");

        assertTrue(articles.isEmpty());
    }

    @Test
    void getAllArticles_ShouldReturnArticlesList_WhenArticlesExist() {
        List<ArticleToPrint> expectedArticles = List.of(new ArticleToPrint());
        IArticlePersistencePort articlePersistencePort = mock(IArticlePersistencePort.class);
        when(articlePersistencePort.getAllArticles(0, 10, "asc", "id")).thenReturn(expectedArticles);

        List<ArticleToPrint> articles = articlePersistencePort.getAllArticles(0, 10, "asc", "id");

        assertEquals(expectedArticles, articles);
    }
}