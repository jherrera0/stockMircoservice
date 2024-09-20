package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;

import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    List<ArticleToPrint> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
    Article findById(Long id);
    void updateArticle(Article article);
    ArticleToPrint findArticleById(Long id);
}
