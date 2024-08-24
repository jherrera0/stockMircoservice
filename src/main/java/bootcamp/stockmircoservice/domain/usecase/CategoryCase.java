package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.*;

import java.util.List;

public class CategoryCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistencePort.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        if(category.getName().isEmpty()){
            throw new CategoryNameEmptyException();
        }
        if(category.getName().length() > Category.MAX_NAME_LENGTH){
            throw new CategoryOversizeNameException();
        }
        if(category.getDescription().isEmpty()){
            throw new CategoryDescriptionEmptyException();
        }
        if(category.getDescription().length() > Category.MAX_DESCRIPTION_LENGTH){
            throw new CategoryOversizeDescriptionException();
        }

        this.categoryPersistencePort.saveCategory(category);
    }


    @Override
    public List<Category> getAllCategories(Integer page, Integer size, String sortDirection) {
        return categoryPersistencePort.getAllCategories(page, size, sortDirection);
    }

}
