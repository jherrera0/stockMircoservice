package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.ArticleRequestMapper;
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


    @Override
    public void saveArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }
}
