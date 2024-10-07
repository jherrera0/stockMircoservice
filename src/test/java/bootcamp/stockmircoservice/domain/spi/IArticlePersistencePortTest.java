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
}