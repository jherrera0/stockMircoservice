package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    List<Category> getAllCategories(Integer page, Integer size, String sortDirection);
}
