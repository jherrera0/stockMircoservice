package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponseCustom;
import bootcamp.stockmircoservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ICategoryResponseCustomMapper {
}
