package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleToCartResponse;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleToCartResponseMapper {
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "productName", source = "name")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "categories",source = "categories")
    @Mapping(target = "quantity", source = "stock")
    @Mapping(target = "price", source = "price")
    ArticleToCartResponse toArticleToCartResponse(ArticleToPrint articleToPrint);

    default List<String> mapCategories(List<Category> categories) {
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
