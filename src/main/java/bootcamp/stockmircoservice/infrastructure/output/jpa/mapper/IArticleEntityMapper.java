package bootcamp.stockmircoservice.infrastructure.output.jpa.mapper;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import javax.xml.transform.Source;
import java.util.List;

@Mapper(componentModel = "spring", uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class})
public interface IArticleEntityMapper {
    @Mapping(source = "brandId", target = "brand",qualifiedByName = "toBrandEntity")
    @Mapping(source = "categoriesId", target = "categories", qualifiedByName = "toCategory")
    ArticleEntity toArticleEntity(Article article);

    @Mapping(source = "brand.id", target = "brandId")
    Article toArticle(ArticleEntity articleEntity);

    @Mapping(source ="categories", target = "categoriesId", qualifiedByName = "toCategoryEntityListToIdList")
    List<Article> toArticleList(List<ArticleEntity> articleEntities);
}
