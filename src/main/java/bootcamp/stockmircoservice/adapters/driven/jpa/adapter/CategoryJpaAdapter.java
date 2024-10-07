package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.CategoryEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection) {
        Pageable pagination;
        if (sortDirection == null || sortDirection.isEmpty()) {
            pagination = PageRequest.of(page, size);
        } else {
            pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), "name"));
        }
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pagination);
        return new PageCustom<>(categoryPage.getNumber(), categoryPage.getSize(), categoryPage.getTotalPages(), categoryEntityMapper.toCategoryList(categoryPage.getContent()));
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).map(categoryEntityMapper::toCategory);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id).map(categoryEntityMapper::toCategory);
    }

    @Override
    public List<Category> findByArticleId(Long articleId) {
        return categoryEntityMapper.toCategoryList(categoryRepository.findAll().stream()
                .filter(categoryEntity -> categoryEntity.getArticleEntities().stream()
                        .anyMatch(articleEntity -> articleEntity.getId().equals(articleId)))
                .toList());
    }

}
