package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryRequestNegativeException;
import bootcamp.stockmircoservice.infrastructure.exception.category.*;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;

import java.util.List;

public class CategoryCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if(category.getName().isEmpty() || category.getName().isBlank()){
            throw new CategoryNameEmptyException();
        }
        if(category.getName().length() > Category.MAX_NAME_LENGTH){
            throw new CategoryOversizeNameException();
        }
        if(category.getDescription().isEmpty()||category.getDescription().isBlank()){
            throw new CategoryDescriptionEmptyException();
        }
        if(category.getDescription().length() > Category.MAX_DESCRIPTION_LENGTH){
            throw new CategoryOversizeDescriptionException();
        }
        if (categoryPersistencePort.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }

        this.categoryPersistencePort.saveCategory(category);
    }


    @Override
    public List<Category> getAllCategories(Integer page, Integer size, String sortDirection) {
        if(page == null || page< ConstValuesToPage.ZERO){
            throw new CategoryPageInvalidException();
        }
        if(size == null || size < ConstValuesToPage.ZERO){
            throw new CategorySizeInvalidException();
        }
        if(sortDirection == null || sortDirection.isEmpty()){
            throw new CategorySortDirectionEmptyException();
        }
        if(!sortDirection.equals(ConstValuesToSort.ASCENDANT_SORT) && (!sortDirection.equals(ConstValuesToSort.DESCENDANT_SORT))){
            throw new CategorySortDirectionInvalidException();
        }
        return categoryPersistencePort.getAllCategories(page, size, sortDirection);
    }

    @Override
    public List<Category> findByArticleId(Long articleId) {
        return categoryPersistencePort.findByArticleId(articleId);
    }

}
