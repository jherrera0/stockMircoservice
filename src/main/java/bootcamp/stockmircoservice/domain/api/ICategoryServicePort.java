package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.model.PageCustom;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection);
    List<Category> findByArticleId(Long articleId);
}
