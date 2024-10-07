package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface CategoryResponseMapper {
    @Mapping(target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(target = "description", source = "description")

    CategoryResponse toResponse(Category category);
    @Named("toResponseListOfCategory")
    List<CategoryResponse> toResponseList(List<Category> categoryRequestList) ;
}
