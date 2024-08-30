package bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;

public interface IArticleHandler {
    void saveArticle(ArticleRequest articleRequest);
}
