package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        this.categoryPersistencePort.saveCategory(category);
    }


    @Override
    public List<Category> getAllCategories(Integer page, Integer size, String sortDirection) {
        return categoryPersistencePort.getAllCategories(page, size, sortDirection);
    }

}
