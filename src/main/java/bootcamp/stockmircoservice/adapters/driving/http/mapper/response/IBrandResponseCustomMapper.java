package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;


import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponseCustom;
import bootcamp.stockmircoservice.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IBrandResponseCustomMapper {
    @Mapping(target = "id")
    @Mapping(source = "name", target = "name")
    BrandResponseCustom toResponseCustom(Brand brand);
}
