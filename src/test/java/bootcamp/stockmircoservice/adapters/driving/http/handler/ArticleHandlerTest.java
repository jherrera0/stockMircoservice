package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ArticleHandlerTest {
    @Mock
    private IArticleServicePort articleServicePort;

    @Mock
    private ArticleRequestMapper articleRequestMapper;

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
}
