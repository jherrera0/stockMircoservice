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

}