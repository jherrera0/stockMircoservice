package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.ArticleResponseMapper;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.exception.article.CategoriesSizeException;
import bootcamp.stockmircoservice.infrastructure.exception.article.DuplicateCategoriesException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {
    private final ArticleRequestMapper articleRequestMapper;
    private final IArticleServicePort articleServicePort;
    private final ArticleResponseMapper articleResponseMapper;


    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        if(articleRequest.getCategoriesId().size() != new HashSet<>(articleRequest.getCategoriesId()).size()){
            throw new DuplicateCategoriesException();
        }
        if(articleRequest.getCategoriesId().isEmpty()|| articleRequest.getCategoriesId().size()>3){
            throw new CategoriesSizeException();
        }
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }

    @Override
    public List<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        return articleResponseMapper.toResponseList(articleServicePort.getAllArticles(page, size, sortDirection, sortBy));
    }
}
