package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;


import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.CategoryEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.ICategoryRepository;
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
    void findByName_ShouldReturnCategory_WhenCategoryExists() {
        String categoryName = "Test";
        CategoryEntity categoryEntity = new CategoryEntity();
        when(categoryRepository.findByNameIgnoreCase(categoryName)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(new Category(categoryName, "Description"));

        Optional<Category> result = categoryJpaAdapter.findByName(categoryName);

        assertTrue(result.isPresent());
        assertEquals(categoryName, result.get().getName());
    }

    @Test
    void findByName_ShouldReturnEmpty_WhenCategoryDoesNotExist() {
        String categoryName = "NonExistent";
        when(categoryRepository.findByNameIgnoreCase(categoryName)).thenReturn(Optional.empty());

        Optional<Category> result = categoryJpaAdapter.findByName(categoryName);

        assertFalse(result.isPresent());
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
    @Test
    void saveCategory_ShouldSaveCategorySuccessfully() {
        Category category = new Category("Test", "Description");
        CategoryEntity categoryEntity = new CategoryEntity();
        when(categoryEntityMapper.toCategoryEntity(category)).thenReturn(categoryEntity);

        categoryJpaAdapter.saveCategory(category);

        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void findById_ShouldReturnCategory_WhenCategoryExists() {
        Long categoryId = 1L;
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(new Category(categoryId,"Test", "Description"));

        Optional<Category> result = categoryJpaAdapter.findById(categoryId);

        assertTrue(result.isPresent());
        assertEquals(categoryId, result.get().getId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenCategoryDoesNotExist() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Optional<Category> result = categoryJpaAdapter.findById(categoryId);

        assertFalse(result.isPresent());
    }

    @Test
    void findByArticleId_ShouldReturnCategories_WhenCategoriesExist() {
        Long articleId = 1L;
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setArticleEntities(Collections.singletonList(new ArticleEntity(articleId)));
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
        when(categoryEntityMapper.toCategoryList(anyList())).thenReturn(Collections.singletonList(new Category("Test", "Description")));

        List<Category> categories = categoryJpaAdapter.findByArticleId(articleId);

        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
    }

    @Test
    void findByArticleId_ShouldReturnEmptyList_WhenNoCategoriesExist() {
        Long articleId = 1L;
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<Category> categories = categoryJpaAdapter.findByArticleId(articleId);

        assertTrue(categories.isEmpty());
    }
}