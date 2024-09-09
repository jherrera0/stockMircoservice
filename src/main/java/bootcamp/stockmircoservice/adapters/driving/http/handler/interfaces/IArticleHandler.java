package bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.domain.model.Article;

import java.util.List;

public interface IArticleHandler {
    void saveArticle(ArticleRequest articleRequest);
    List<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
}
