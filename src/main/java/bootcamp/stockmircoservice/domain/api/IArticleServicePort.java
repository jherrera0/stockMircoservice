package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.model.PageCustom;

public interface IArticleServicePort {
    void saveArticle(Article article);
    PageCustom<ArticleToPrint> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
    void updateArticle(Long id, Long quantity);
    ArticleToPrint getArticleById(Long id);
}

