package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;

import java.util.List;

public interface IArticleServicePort {
    void saveArticle(Article article);
    List<ArticleToPrint> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
    void updateArticle(Long id, Long quantity);
}

