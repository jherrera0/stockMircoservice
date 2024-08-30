package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Article;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IArticleServicePortTest {
    @Test
    void saveArticle_ShouldSaveArticleSuccessfully() {
        IArticleServicePort articleServicePort = mock(IArticleServicePort.class);
        Article article = new Article();

        articleServicePort.saveArticle(article);

        verify(articleServicePort, times(1)).saveArticle(article);
    }

    @Test
    void getAllArticles_ShouldReturnEmptyList_WhenNoArticlesExist() {
        IArticleServicePort articleServicePort = mock(IArticleServicePort.class);
        when(articleServicePort.getAllArticles(0, 10, "asc", "name")).thenReturn(Collections.emptyList());

        List<Article> articles = articleServicePort.getAllArticles(0, 10, "asc", "name");

        assertTrue(articles.isEmpty());
    }

    @Test
    void getAllArticles_ShouldReturnArticlesList_WhenArticlesExist() {
        List<Article> expectedArticles = List.of(new Article());
        IArticleServicePort articleServicePort = mock(IArticleServicePort.class);
        when(articleServicePort.getAllArticles(0, 10, "asc", "name")).thenReturn(expectedArticles);

        List<Article> articles = articleServicePort.getAllArticles(0, 10, "asc", "name");

        assertEquals(expectedArticles, articles);
    }
}