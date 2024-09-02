package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface BrandResponseMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    BrandResponse toResponse(Brand brand);
    List<Brand> toBrandList(List<BrandResponse> brandResponse);
    default List<BrandResponse> toResponseList(List<Brand> brandRequestList){
        return brandRequestList.stream().map(
                brandRequest->{
                    BrandResponse brandResponse = new BrandResponse();
                    brandResponse.setId(brandRequest.getId());
                    brandResponse.setName(brandRequest.getName());
                    brandResponse.setDescription(brandRequest.getDescription());
                    return brandResponse;
                }).toList();
    }

}
