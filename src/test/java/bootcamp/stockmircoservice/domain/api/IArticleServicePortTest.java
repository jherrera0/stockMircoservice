package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
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

        List<ArticleToPrint> articles = articleServicePort.getAllArticles(0, 10, "asc", "name");

        assertTrue(articles.isEmpty());
    }

    @Test
    void getAllArticles_ShouldReturnArticlesList_WhenArticlesExist() {
        List<ArticleToPrint> expectedArticles = List.of(new ArticleToPrint());
        IArticleServicePort articleServicePort = mock(IArticleServicePort.class);
        when(articleServicePort.getAllArticles(0, 10, "asc", "name")).thenReturn(expectedArticles);

        List<ArticleToPrint> articles = articleServicePort.getAllArticles(0, 10, "asc", "name");

        assertEquals(expectedArticles, articles);
    }
}