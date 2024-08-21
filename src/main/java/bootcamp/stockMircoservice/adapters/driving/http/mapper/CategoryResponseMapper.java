package bootcamp.stockMircoservice.adapters.driving.http.mapper;

import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryRequest;
import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryResponse;
import bootcamp.stockMircoservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface CategoryResponseMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryRequest toCategoryRequest(Category category);

    default List<CategoryResponse> toResponseList(List<Category> categoryRequestList) {
        return categoryRequestList.stream().map(
                categoryRequest-> {
                    CategoryResponse categoryResponse = new CategoryResponse();
                    categoryResponse.setName(categoryRequest.getName());
                    categoryResponse.setDescription(categoryRequest.getDescription());
                    return categoryResponse;
                }).toList();
    }
}
