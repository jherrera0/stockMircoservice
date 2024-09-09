package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.CategoriesSizeException;
import bootcamp.stockmircoservice.infrastructure.exception.article.DuplicateCategoriesException;
import bootcamp.stockmircoservice.infrastructure.until.Validation;

import java.util.HashSet;
import java.util.List;

public class ArticleCase implements IArticleServicePort {
    private final IArticlePersistencePort articlePersistencePort;
    private final IBrandPersistencePort branchPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public ArticleCase(IArticlePersistencePort articlePersistencePort, IBrandPersistencePort branchPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.branchPersistencePort = branchPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    public void saveArticle(Article article) {
        Validation.validationSaveArticle(article, categoryPersistencePort);
        if(article.getCategoriesId().size() != new HashSet<>(article.getCategoriesId()).size()){
            throw new DuplicateCategoriesException();
        }
        if(article.getCategoriesId().isEmpty()|| article.getCategoriesId().size()>3){
            throw new CategoriesSizeException();
        }
        branchPersistencePort.findById(article.getBrandId());
        articlePersistencePort.saveArticle(article);
    }

    @Override
    public List<ArticleToPrint> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        Validation.validationGetAllArticles(page, size, sortDirection, sortBy);
        return articlePersistencePort.getAllArticles(page, size, sortDirection, sortBy);
    }


}
