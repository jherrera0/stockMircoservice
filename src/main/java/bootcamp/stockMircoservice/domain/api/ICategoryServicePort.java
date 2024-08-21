package bootcamp.stockMircoservice.domain.api;

import bootcamp.stockMircoservice.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    List<Category> getAllCategories(Integer page, Integer size, String sortDirection);
}
