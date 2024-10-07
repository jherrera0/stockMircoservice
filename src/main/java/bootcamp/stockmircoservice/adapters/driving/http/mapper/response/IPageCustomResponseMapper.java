package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandResponseMapper.class})
public interface IPageCustomResponseMapper {
    @Mapping(target = "items", source = "items", qualifiedByName = "toResponseList")
    @Mapping(target = "currentPage", source = "currentPage")
    @Mapping(target = "pageSize", source = "pageSize")
    @Mapping(target = "totalPages", source = "totalPages")
    PageCustomResponse toResponsePage(PageCustom<Brand> page);

}
