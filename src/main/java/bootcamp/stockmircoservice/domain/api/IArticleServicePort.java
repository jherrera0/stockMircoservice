package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Article;

import java.util.List;

public interface IArticleServicePort {
    void saveArticle(Article article);
    List<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);

}
