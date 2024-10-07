package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleToCartResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.ArticleResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IArticleToCartResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IPageCustomResponseMapper;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.model.PageCustom;
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
    private IPageCustomResponseMapper pageCustomResponseMapper;

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
    void saveArticle_ShouldCallServicePortWithMappedArticle() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article();
        when(articleRequestMapper.toArticle(articleRequest)).thenReturn(article);

        articleHandler.saveArticle(articleRequest);

        verify(articleRequestMapper, times(1)).toArticle(articleRequest);
        verify(articleServicePort, times(1)).saveArticle(article);
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
    @Test
    void getAllArticles_withValidParameters_returnsPageCustomResponse() {
        int page = 0;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";
        PageCustomResponse<ArticleResponse> expectedResponse = new PageCustomResponse<>();
        PageCustom<ArticleToPrint> pageCustom = new PageCustom<>();
        when(articleServicePort.getAllArticles(page, size, sortDirection, sortBy)).thenReturn(pageCustom);
        when(pageCustomResponseMapper.toResponsePageOfArticle(any())).thenReturn(expectedResponse);

        PageCustomResponse<ArticleResponse> response = articleHandler.getAllArticles(page, size, sortDirection, sortBy);

        assertEquals(expectedResponse, response);
    }
}
