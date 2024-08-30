package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Article;

public interface IArticleServicePort {
    void saveArticle(Article article);
}
