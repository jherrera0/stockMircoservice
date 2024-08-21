package bootcamp.stockMircoservice.domain.usecase;

import bootcamp.stockMircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockMircoservice.domain.model.Category;
import bootcamp.stockMircoservice.domain.spi.ICategoryPersistencePort;

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
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }
}
