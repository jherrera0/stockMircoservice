package bootcamp.stockmircoservice.infrastructure.until;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNotExistException;

import java.math.BigDecimal;

public class Validation {
    private Validation() {
    }

    public static void validationSaveArticle(ArticleRequest articleRequest, ICategoryPersistencePort categoryPersistencePort) {
        if(articleRequest == null){
            throw new ArticleRequestNullException();
        }
        if(articleRequest.getBrandId() == null || articleRequest.getBrandId()<= ConstValuesToPage.ZERO){
            throw new ArticleBrandIdErrorException();
        }
        if(articleRequest.getPrice() == null){
            throw new ArticlePriceNullException();
        }
        if(articleRequest.getPrice().compareTo(BigDecimal.ZERO)< ConstValues.ZERO){
            throw new ArticlePriceNegativeException();
        }
        if(articleRequest.getName() == null || articleRequest.getName().isEmpty()){
            throw new ArticleNameEmptyException();
        }
        if(articleRequest.getDescription() == null || articleRequest.getDescription().isEmpty()){
            throw new ArticleDescriptionEmptyException();
        }
        if(articleRequest.getStock() < ConstValues.ZERO){
            throw new ArticleIllegalStockValueException();
        }
        if(articleRequest.getCategoriesId() == null || articleRequest.getCategoriesId().isEmpty()){
            throw new ArticleCategoriesIdEmptyException();
        }

        for(Long categoryId: articleRequest.getCategoriesId()){
            if(categoryPersistencePort.findById(categoryId).isEmpty()){
                throw new CategoryNotExistException();
            }
        }
    }

    public static void validationGetAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        if(page == null || page< ConstValuesToPage.ZERO){
            throw new ArticlePageEmptyException();
        }
        if(size == null || size< ConstValuesToPage.ZERO){
            throw new ArticleSizeEmptyException();
        }
        if(sortDirection == null || sortDirection.isEmpty()){
            throw new ArticleSortDirectionEmptyException();
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
    }
}
