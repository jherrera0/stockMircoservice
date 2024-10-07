package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleToCartResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IArticleToCartResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IPageCustomResponseMapper;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {
    private final ArticleRequestMapper articleRequestMapper;
    private final IArticleServicePort articleServicePort;
    private final IPageCustomResponseMapper pageCustomResponseMapper;
    private final IArticleToCartResponseMapper articleToCartResponseMapper;


    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }

    @Override
    public PageCustomResponse<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        return pageCustomResponseMapper.toResponsePageOfArticle(articleServicePort.getAllArticles(page, size, sortDirection, sortBy));
    }

    @Override
    public void updateArticle(Long id, Long quantity) {
        articleServicePort.updateArticle(id, quantity);
    }

    @Override
    public ArticleToCartResponse getArticleById(Long id) {
        return articleToCartResponseMapper.toArticleToCartResponse(articleServicePort.getArticleById(id));
    }
}
