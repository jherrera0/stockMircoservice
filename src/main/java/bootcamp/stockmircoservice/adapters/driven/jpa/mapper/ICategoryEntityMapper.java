package bootcamp.stockmircoservice.adapters.driven.jpa.mapper;

import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ICategoryEntityMapper {
    CategoryEntity toCategoryEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategoryList(List<CategoryEntity> categoryEntities);

    @Named("toCategory")
    default CategoryEntity toCategory(Long categoryId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        return categoryEntity;
    }

}
