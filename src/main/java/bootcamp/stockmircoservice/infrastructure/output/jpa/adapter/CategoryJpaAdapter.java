package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.*;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.CategoryEntity;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor

public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;


    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
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
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size, String sortDirection) {
        Pageable pagination;
        if (sortDirection == null || sortDirection.isEmpty()) {
            pagination = PageRequest.of(page, size);
        } else {
            pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), "name"));
        }
        List<CategoryEntity> categories = categoryRepository.findAll(pagination).getContent();
        if (categories.isEmpty()) {
            throw new CategoriesNotFoundException();
        }
        return categoryEntityMapper.toCategoryList(categories);
    }

}
