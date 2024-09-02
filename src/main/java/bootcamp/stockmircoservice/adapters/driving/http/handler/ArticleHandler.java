package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.ArticleRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.ArticleResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNotExistException;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;
import bootcamp.stockmircoservice.infrastructure.until.Validation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {
    private final ArticleRequestMapper articleRequestMapper;
    private final IArticleServicePort articleServicePort;
    private final ArticleResponseMapper articleResponseMapper;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandServicePort branchPersistencePort;


    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        Validation.validationSaveArticle(articleRequest, categoryPersistencePort);
        branchPersistencePort.findById(articleRequest.getBrandId());
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }

    @Override
    public List<ArticleResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        Validation.validationGetAllArticles(page, size, sortDirection, sortBy);
        return articleResponseMapper.toResponseList(articleServicePort.getAllArticles(page, size, sortDirection, sortBy));

    }
}
