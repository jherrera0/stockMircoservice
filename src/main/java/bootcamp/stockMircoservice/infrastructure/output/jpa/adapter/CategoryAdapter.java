package bootcamp.stockMircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockMircoservice.domain.model.Category;
import bootcamp.stockMircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockMircoservice.infrastructure.exception.CategoriesNotFoundException;
import bootcamp.stockMircoservice.infrastructure.exception.CategoryAlreadyExistsException;
import bootcamp.stockMircoservice.infrastructure.output.jpa.entity.CategoryEntity;
import bootcamp.stockMircoservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockMircoservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor

public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;


    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            throw new CategoriesNotFoundException();
        }
        return  categoryEntityMapper.toCategoryList(categories);
    }
}
