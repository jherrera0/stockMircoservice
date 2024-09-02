package bootcamp.stockmircoservice.infrastructure.output.jpa.mapper;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ICategoryEntityMapperTest {
    @InjectMocks
    ICategoryEntityMapper categoryEntityMapper = new ICategoryEntityMapperImpl();
    @Test
    void toCategoryEntity_ShouldMapCategoryToEntitySuccessfully() {
        Category category = new Category(1L, "CategoryName","CategoryDescription");
        CategoryEntity categoryEntity = categoryEntityMapper.toCategoryEntity(category);
        assertNotNull(categoryEntity);
        assertEquals(category.getId(), categoryEntity.getId());
        assertEquals(category.getName(), categoryEntity.getName());
    }

    @Test
    void toCategoryEntity_ShouldReturnNull_WhenCategoryIsNull() {
        CategoryEntity categoryEntity = categoryEntityMapper.toCategoryEntity(null);
        assertNull(categoryEntity);
    }

    @Test
    void toCategory_ShouldMapEntityToCategorySuccessfully() {
        CategoryEntity categoryEntity = new CategoryEntity(1L, "CategoryName","CategoryDescription", List.of(new ArticleEntity()));
        Category category = categoryEntityMapper.toCategory(categoryEntity);
        assertNotNull(category);
        assertEquals(categoryEntity.getId(), category.getId());
        assertEquals(categoryEntity.getName(), category.getName());
    }

    @Test
    void toCategory_ShouldReturnNull_WhenCategoryEntityIsNull() {
        CategoryEntity categoryEntity = null;
        Category category = categoryEntityMapper.toCategory(categoryEntity);
        assertNull(category);
    }
    @Test
    void toCategoryList_ShouldMapEntityListToCategoryListSuccessfully() {
        List<CategoryEntity> categoryEntities = List.of(
                new CategoryEntity(1L, "CategoryName1", "CategoryDescription1", List.of(new ArticleEntity())),
                new CategoryEntity(2L, "CategoryName2", "CategoryDescription2", List.of(new ArticleEntity()))
        );
        List<Category> categories = categoryEntityMapper.toCategoryList(categoryEntities);
        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals(categoryEntities.get(0).getId(), categories.get(0).getId());
        assertEquals(categoryEntities.get(1).getId(), categories.get(1).getId());
    }

    @Test
    void toCategoryList_ShouldReturnEmptyList_WhenEntityListIsEmpty() {
        List<CategoryEntity> categoryEntities = List.of();
        List<Category> categories = categoryEntityMapper.toCategoryList(categoryEntities);
        assertNotNull(categories);
        assertTrue(categories.isEmpty());
    }

    @Test
    void toCategoryEntityNamed_ShouldMapIdToCategoryEntitySuccessfully() {
        Long categoryId = 1L;
        CategoryEntity categoryEntity = categoryEntityMapper.toCategory(categoryId);
        assertNotNull(categoryEntity);
        assertEquals(categoryId, categoryEntity.getId());
    }

    @Test
    void toCategoryEntityNamed_ShouldReturnNull_WhenIdIsNull() {
        Long categoryId = null;
        CategoryEntity categoryEntity = categoryEntityMapper.toCategory(categoryId);
        assertNull(categoryEntity.getId());
    }
}