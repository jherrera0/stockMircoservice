package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.CategoryEntity;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;


    @Override
    public void saveCategory(Category category) {
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
        return categoryEntityMapper.toCategoryList(categories);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).map(categoryEntityMapper::toCategory);
    }

}
