package bootcamp.stockmircoservice.adapters.driving.http.mapper;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper (componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface CategoryRequestMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    Category toCategory(CategoryRequest categoryRequest);


}
