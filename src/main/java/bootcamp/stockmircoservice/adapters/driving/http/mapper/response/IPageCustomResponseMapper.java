package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandResponseMapper.class, CategoryResponseMapper.class, ArticleResponseMapper.class})
public interface IPageCustomResponseMapper {
    @Mapping(target = "items", source = "items", qualifiedByName = "toResponseListOfBrand")
    @Mapping(target = "currentPage", source = "currentPage")
    @Mapping(target = "pageSize", source = "pageSize")
    @Mapping(target = "totalPages", source = "totalPages")
    PageCustomResponse<BrandResponse> toResponsePageOfBrand(PageCustom<Brand> page);

    @Mapping(target = "items", source = "items", qualifiedByName = "toResponseListOfCategory")
    @Mapping(target = "currentPage", source = "currentPage")
    @Mapping(target = "pageSize", source = "pageSize")
    @Mapping(target = "totalPages", source = "totalPages")
    PageCustomResponse<CategoryResponse> toResponsePageOfCategory(PageCustom<Category> page);

    @Mapping(target = "items", source = "items", qualifiedByName = "toResponseListOfArticle")
    @Mapping(target = "currentPage", source = "currentPage")
    @Mapping(target = "pageSize", source = "pageSize")
    @Mapping(target = "totalPages", source = "totalPages")
    PageCustomResponse<ArticleResponse> toResponsePageOfArticle(PageCustom<ArticleToPrint> page);

}
