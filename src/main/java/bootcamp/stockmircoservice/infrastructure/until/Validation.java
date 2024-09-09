package bootcamp.stockmircoservice.infrastructure.until;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;

import java.math.BigDecimal;

public class Validation {
    private Validation() {
    }

    public static void validationSaveArticle(Article article) {
        if(article == null){
            throw new ArticleRequestNullException();
        }
        if(article.getBrandId() == null || article.getBrandId()<= ConstValuesToPage.ZERO){
            throw new ArticleBrandIdErrorException();
        }
        if(article.getPrice() == null){
            throw new ArticlePriceNullException();
        }
        if(article.getPrice().compareTo(BigDecimal.ZERO)< ConstValues.ZERO){
            throw new ArticlePriceNegativeException();
        }
        if(article.getName() == null || article.getName().isEmpty()){
            throw new ArticleNameEmptyException();
        }
        if(article.getDescription() == null || article.getDescription().isEmpty()){
            throw new ArticleDescriptionEmptyException();
        }
        if(article.getStock() < ConstValues.ZERO){
            throw new ArticleIllegalStockValueException();
        }
        if(article.getCategoriesId() == null || article.getCategoriesId().isEmpty()){
            throw new ArticleCategoriesIdEmptyException();
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
        if(!sortBy.equals(ConstValuesToSort.SORT_BY_NAME) &&!sortBy.equals(ConstValuesToSort.SORT_BY_BRAND) && !sortBy.equals(ConstValuesToSort.SORT_BY_CATEGORIES_NAME)) {
                    throw new ArticleSortByInvalidException();
        }
    }
}
