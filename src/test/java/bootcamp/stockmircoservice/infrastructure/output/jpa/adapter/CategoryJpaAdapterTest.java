package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;


import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.infrastructure.exception.*;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.CategoryEntity;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryJpaAdapterTest {

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void saveCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        Category category = new Category("Test", "Description");
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(new CategoryEntity()));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryJpaAdapter.saveCategory(category));
    }
    @Test
    void getAllCategories_ShouldReturnCategories_WhenCategoriesExist() {
        CategoryEntity categoryEntity = new CategoryEntity();
        Page<CategoryEntity> page = new PageImpl<>(Collections.singletonList(categoryEntity));
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryRepository.findAll(pageable)).thenReturn(page);
        when(categoryEntityMapper.toCategoryList(anyList())).thenReturn(Collections.singletonList(new Category("Test", "Description")));

        List<Category> categories = categoryJpaAdapter.getAllCategories(0, 10, null);

        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
    }
    @Test
    void getAllCategories_ShouldThrowException_WhenNoCategoriesFound() {
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryRepository.findAll(pageable)).thenReturn(Page.empty());

        assertThrows(CategoriesNotFoundException.class, () -> categoryJpaAdapter.getAllCategories(0, 10, null));
    }
    @Test
    void getAllCategories_ShouldReturnCategories_WhenSortDirectionIsNull() {
        CategoryEntity categoryEntity = new CategoryEntity();
        Page<CategoryEntity> page = new PageImpl<>(Collections.singletonList(categoryEntity));
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryRepository.findAll(pageable)).thenReturn(page);
        when(categoryEntityMapper.toCategoryList(anyList())).thenReturn(Collections.singletonList(new Category("Test", "Description")));

        List<Category> categories = categoryJpaAdapter.getAllCategories(0, 10, null);

        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
    }
    @Test
    void getAllCategories_ShouldReturnCategories_WhenSortDirectionIsEmpty() {
        CategoryEntity categoryEntity = new CategoryEntity();
        Page<CategoryEntity> page = new PageImpl<>(Collections.singletonList(categoryEntity));
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryRepository.findAll(pageable)).thenReturn(page);
        when(categoryEntityMapper.toCategoryList(anyList())).thenReturn(Collections.singletonList(new Category("Test", "Description")));

        List<Category> categories = categoryJpaAdapter.getAllCategories(0, 10, "");

        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
    }
}