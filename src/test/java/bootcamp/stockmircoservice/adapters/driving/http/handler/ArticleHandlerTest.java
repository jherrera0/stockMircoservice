package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleToCartResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.ArticleResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IArticleToCartResponseMapper;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ArticleHandlerTest {
    @Mock
    private IArticleServicePort articleServicePort;

    @Mock
    private ArticleResponseMapper articleResponseMapper;

    @Mock
    private ArticleRequestMapper articleRequestMapper;

    @Mock
    private IArticleToCartResponseMapper articleToCartResponseMapper;

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
    @Test
    void updateArticle_withValidParameters_callsServicePort() {
        doNothing().when(articleServicePort).updateArticle(1L, 10L);

        articleHandler.updateArticle(1L, 10L);

        verify(articleServicePort, times(1)).updateArticle(1L, 10L);
    }

    @Test
    void getArticleById_withValidId_returnsArticleToCartResponse() {
        ArticleToCartResponse expectedResponse = new ArticleToCartResponse();
        when(articleServicePort.getArticleById(1L)).thenReturn(new ArticleToPrint());
        when(articleToCartResponseMapper.toArticleToCartResponse(any(ArticleToPrint.class))).thenReturn(expectedResponse);

        ArticleToCartResponse response = articleHandler.getArticleById(1L);

        assertEquals(expectedResponse, response);
        verify(articleServicePort, times(1)).getArticleById(1L);
        verify(articleToCartResponseMapper, times(1)).toArticleToCartResponse(any(ArticleToPrint.class));
    }

    @Test
    void getArticleById_withNonExistentId_throwsArticleNotFoundException() {
        when(articleServicePort.getArticleById(1L)).thenThrow(new ArticleNotFoundException());

        assertThrows(ArticleNotFoundException.class, () -> articleHandler.getArticleById(1L));
        verify(articleServicePort, times(1)).getArticleById(1L);
    }


    @Test
    void saveArticle_withValidRequest_callsServicePort() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article();
        when(articleRequestMapper.toArticle(articleRequest)).thenReturn(article);

        articleHandler.saveArticle(articleRequest);

        verify(articleRequestMapper, times(1)).toArticle(articleRequest);
        verify(articleServicePort, times(1)).saveArticle(article);
    }

}
