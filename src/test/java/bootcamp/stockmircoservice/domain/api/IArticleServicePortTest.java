package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Article;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class IArticleServicePortTest {
    @Test
    void saveArticle_ShouldSaveArticleSuccessfully() {
        IArticleServicePort articleServicePort = mock(IArticleServicePort.class);
        Article article = new Article();

        articleServicePort.saveArticle(article);

        verify(articleServicePort, times(1)).saveArticle(article);
    }

}