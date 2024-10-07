package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.model.PageCustom;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection);
    Optional<Category> findByName(String name);
    Optional<Category> findById(Long id);
    List<Category> findByArticleId(Long articleId);
}
