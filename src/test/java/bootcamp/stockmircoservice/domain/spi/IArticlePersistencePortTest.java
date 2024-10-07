package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Article;
import org.junit.jupiter.api.Test;

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