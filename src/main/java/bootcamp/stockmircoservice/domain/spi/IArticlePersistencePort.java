package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Article;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
}
