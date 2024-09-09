package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.ArticleResponseMapper;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleHandlerTest {
    @Mock
    private IArticleServicePort articleServicePort;

    @Mock
    private ArticleResponseMapper articleResponseMapper;

    @Mock
    private ArticleRequestMapper articleRequestMapper;

    @InjectMocks
    private ArticleHandler articleHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllArticles_ShouldReturnArticleResponses_WhenValidParameters() {
        List<ArticleToPrint> articles = List.of(new ArticleToPrint(), new ArticleToPrint());
        List<ArticleResponse> articleResponses = List.of(new ArticleResponse(), new ArticleResponse());
        when(articleServicePort.getAllArticles(0, 10, "asc", "name")).thenReturn(articles);
        when(articleResponseMapper.toResponseList(articles)).thenReturn(articleResponses);

        List<ArticleResponse> result = articleHandler.getAllArticles(0, 10, "asc", "name");

        assertEquals(articleResponses, result);
        verify(articleServicePort, times(1)).getAllArticles(0, 10, "asc", "name");
        verify(articleResponseMapper, times(1)).toResponseList(articles);
    }
    @Test
    void getAllArticles_ShouldReturnEmptyList_WhenNoArticlesExist() {
        when(articleServicePort.getAllArticles(0, 10, "asc", "name")).thenReturn(List.of());
        when(articleResponseMapper.toResponseList(List.of())).thenReturn(List.of());

        List<ArticleResponse> result = articleHandler.getAllArticles(0, 10, "asc", "name");

        assertEquals(List.of(), result);
        verify(articleServicePort, times(1)).getAllArticles(0, 10, "asc", "name");
        verify(articleResponseMapper, times(1)).toResponseList(List.of());
    }

    @Test
    void getAllArticles_ShouldReturnArticles_WhenSortByBrand() {
        List<ArticleToPrint> articles = List.of(new ArticleToPrint(), new ArticleToPrint());
        List<ArticleResponse> articleResponses = List.of(new ArticleResponse(), new ArticleResponse());
        when(articleServicePort.getAllArticles(0, 10, "asc", "brand")).thenReturn(articles);
        when(articleResponseMapper.toResponseList(articles)).thenReturn(articleResponses);

        List<ArticleResponse> result = articleHandler.getAllArticles(0, 10, "asc", "brand");

        assertEquals(articleResponses, result);
        verify(articleServicePort, times(1)).getAllArticles(0, 10, "asc", "brand");
        verify(articleResponseMapper, times(1)).toResponseList(articles);
    }
    @Test
    void saveArticle_ShouldCallServicePortWithMappedArticle() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article();
        when(articleRequestMapper.toArticle(articleRequest)).thenReturn(article);

        articleHandler.saveArticle(articleRequest);

        verify(articleRequestMapper, times(1)).toArticle(articleRequest);
        verify(articleServicePort, times(1)).saveArticle(article);
    }

    @Test
    void getAllArticles_ShouldReturnMappedArticleResponses() {
        List<ArticleToPrint> articles = List.of(new ArticleToPrint(), new ArticleToPrint());
        List<ArticleResponse> articleResponses = List.of(new ArticleResponse(), new ArticleResponse());
        when(articleServicePort.getAllArticles(0, 10, "asc", "name")).thenReturn(articles);
        when(articleResponseMapper.toResponseList(articles)).thenReturn(articleResponses);

        List<ArticleResponse> result = articleHandler.getAllArticles(0, 10, "asc", "name");

        assertEquals(articleResponses, result);
        verify(articleServicePort, times(1)).getAllArticles(0, 10, "asc", "name");
        verify(articleResponseMapper, times(1)).toResponseList(articles);
    }

}
