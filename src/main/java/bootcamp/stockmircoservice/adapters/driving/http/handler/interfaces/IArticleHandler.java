package bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleToCartResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;

import java.util.List;

public interface IArticleHandler {
    void saveArticle(ArticleRequest articleRequest);
    PageCustomResponse<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy);
    void updateArticle(Long id, Long quantity);
    ArticleToCartResponse getArticleById(Long id);
}
