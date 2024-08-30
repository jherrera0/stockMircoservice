package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    List<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
}
