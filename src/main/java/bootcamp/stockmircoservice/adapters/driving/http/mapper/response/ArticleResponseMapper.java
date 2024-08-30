package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ArticleResponseMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "brand", target = "brand", qualifiedByName = "toResponse")
    @Mapping(source = "category", target = "category", qualifiedByName = "toResponse")

    default List<ArticleResponse> toResponseList(List<Article> articleRequestList) {
        return articleRequestList.stream().map(
                articleRequest-> {
                    ArticleResponse articleResponse = new ArticleResponse();
                    articleResponse.setId(articleRequest.getId());
                    articleResponse.setName(articleRequest.getName());
                    articleResponse.setDescription(articleRequest.getDescription());
                    articleResponse.setPrice(articleRequest.getPrice());
                    articleResponse.setStock(articleRequest.getStock());

                    return articleResponse;
                }).toList();
    }
}
