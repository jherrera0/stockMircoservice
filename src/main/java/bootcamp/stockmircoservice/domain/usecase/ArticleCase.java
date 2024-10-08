package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleNotFoundException;
import bootcamp.stockmircoservice.infrastructure.exception.article.CategoriesSizeException;
import bootcamp.stockmircoservice.infrastructure.exception.article.DuplicateCategoriesException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNotExistException;
import bootcamp.stockmircoservice.infrastructure.until.Validation;

import java.util.HashSet;

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
        if(article.getCategoriesId().size() != new HashSet<>(article.getCategoriesId()).size()){
            throw new DuplicateCategoriesException();
        }
        if(article.getCategoriesId().isEmpty()|| article.getCategoriesId().size()>3){
            throw new CategoriesSizeException();
        }
        Validation.validationSaveArticle(article);
        branchPersistencePort.findById(article.getBrandId());
        for(Long categoryId: article.getCategoriesId()){
            if(categoryPersistencePort.findById(categoryId).isEmpty()){
                throw new CategoryNotExistException();
            }
        }
        articlePersistencePort.saveArticle(article);
    }

    @Override
    public PageCustom<ArticleToPrint> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        Validation.validationGetAllArticles(page, size, sortDirection, sortBy);
        return articlePersistencePort.getAllArticles(page, size, sortDirection, sortBy);
    }

    @Override
    public void updateArticle(Long id, Long quantity) {
        Validation.validationUpdateArticle(id, quantity);
        Article article = articlePersistencePort.findById(id);
        article.setStock(article.getStock() + quantity);
        articlePersistencePort.updateArticle(article);
    }

    @Override
    public ArticleToPrint getArticleById(Long id) {
        if (articlePersistencePort.findArticleById(id) != null) {
            return articlePersistencePort.findArticleById(id);
        }
        else {
            throw new ArticleNotFoundException();
        }
    }


}
