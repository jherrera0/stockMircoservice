package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.ArticleResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleSortDirectionInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleSortByEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleSortByInvalidException;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleSortDirectionEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleSizeEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticlePageEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleRequestNullException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        if(articleRequest == null){
            throw new ArticleRequestNullException();
        }
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }

    @Override
    public List<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        if(page == null || page< ConstValuesToPage.ZERO){
            throw new ArticlePageEmptyException();
        }
        if(size == null || size< ConstValuesToPage.ZERO){
            throw new ArticleSizeEmptyException();
        }
        if(sortDirection == null || sortDirection.isEmpty()){
            throw new  ArticleSortDirectionEmptyException();
        }
        if(!sortDirection.equals(ConstValuesToSort.ASCENDANT_SORT )&& !sortDirection.equals(ConstValuesToSort.DESCENDANT_SORT)){
                throw new ArticleSortDirectionInvalidException();
        }
        if(sortBy == null || sortBy.isEmpty()){
            throw new ArticleSortByEmptyException();
        }
        if(!sortBy.equals(ConstValuesToSort.SORT_BY_NAME)){
            if(!sortBy.equals(ConstValuesToSort.SORT_BY_BRAND)) {
                if (!sortBy.equals(ConstValuesToSort.SORT_BY_CATEGORIES_NAME)) {
                    throw new ArticleSortByInvalidException();
                }
            }
        }
        return articleResponseMapper.toResponseList(articleServicePort.getAllArticles(page, size, sortDirection, sortBy));

    }
}
