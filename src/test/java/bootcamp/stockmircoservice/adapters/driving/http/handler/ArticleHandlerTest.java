package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.ArticleResponseMapper;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ArticleHandlerTest {
    @Mock
    private IArticleServicePort articleServicePort;

    @Mock
    private ArticleRequestMapper articleRequestMapper;

    @Mock
    private ArticleResponseMapper articleResponseMapper;

    @InjectMocks
    private ArticleHandler articleHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticle_ShouldCallServicePort() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article();
        when(articleRequestMapper.toArticle(articleRequest)).thenReturn(article);
        doNothing().when(articleServicePort).saveArticle(article);

        articleHandler.saveArticle(articleRequest);

        verify(articleServicePort, times(1)).saveArticle(article);
    }

    @Test
    void getAllArticles_ShouldReturnArticleResponses_WhenValidParameters() {
        List<Article> articles = List.of(new Article(), new Article());
        List<ArticleResponse> articleResponses = List.of(new ArticleResponse(), new ArticleResponse());
        when(articleServicePort.getAllArticles(0, 10, "asc", "name")).thenReturn(articles);
        when(articleResponseMapper.toResponseList(articles)).thenReturn(articleResponses);

        List<ArticleResponse> result = articleHandler.getAllArticles(0, 10, "asc", "name");

        assertEquals(articleResponses, result);
        verify(articleServicePort, times(1)).getAllArticles(0, 10, "asc", "name");
        verify(articleResponseMapper, times(1)).toResponseList(articles);
    }

    @Test
    void saveArticle_ShouldThrowException_WhenArticleRequestIsNull() {
        assertThrows(ArticleRequestNullException.class, () -> articleHandler.saveArticle(null));
    }

    @Test
    void getAllArticles_ShouldThrowException_WhenPageIsNegative() {
        assertThrows(ArticlePageEmptyException.class, () -> articleHandler.getAllArticles(-1, 10, "asc", "name"));
    }

    @Test
    void getAllArticles_ShouldThrowException_WhenSizeIsNegative() {
        assertThrows(ArticleSizeEmptyException.class, () -> articleHandler.getAllArticles(0, -10, "asc", "name"));
    }

    @Test
    void getAllArticles_ShouldThrowException_WhenSortDirectionIsInvalid() {
        assertThrows(ArticleSortDirectionInvalidException.class, () -> articleHandler.getAllArticles(0, 10, "invalid", "name"));
    }
    @Test
    void getAllArticles_ShouldThrowException_WhenSortByIsNull() {
        assertThrows(ArticleSortByEmptyException.class, () -> articleHandler.getAllArticles(0, 10, "asc", null));
    }

    @Test
    void getAllArticles_ShouldThrowException_WhenSortByIsEmpty() {
        assertThrows(ArticleSortByEmptyException.class, () -> articleHandler.getAllArticles(0, 10, "asc", ""));
    }

    @Test
    void getAllArticles_ShouldThrowException_WhenSortByIsInvalid() {
        assertThrows(ArticleSortByInvalidException.class, () -> articleHandler.getAllArticles(0, 10, "asc", "invalid"));
    }

    @Test
    void saveArticle_ShouldCallMapperAndServicePort() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article();
        when(articleRequestMapper.toArticle(articleRequest)).thenReturn(article);
        doNothing().when(articleServicePort).saveArticle(article);

        articleHandler.saveArticle(articleRequest);

        verify(articleRequestMapper, times(1)).toArticle(articleRequest);
        verify(articleServicePort, times(1)).saveArticle(article);
    }
}
