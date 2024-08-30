package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.DuplicateCategoriesException;

import java.util.HashSet;

public class ArticleCase implements IArticleServicePort {
    private final IArticlePersistencePort articlePersistencePort;

    public ArticleCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    public void saveArticle(Article article) {
        if(article.getCategoriesId().size() != new HashSet<>(article.getCategoriesId()).size()){
            throw new DuplicateCategoriesException();
        }


        articlePersistencePort.saveArticle(article);
    }


}
