package bootcamp.stockmircoservice.adapters.driven.jpa.mapper;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class})
public interface IArticleEntityMapper {
    @Mapping(source = "brandId", target = "brand",qualifiedByName = "toBrandEntity")
    @Mapping(source = "categoriesId", target = "categories", qualifiedByName = "toCategory")
    ArticleEntity toArticleEntity(Article article);

    @Mapping(source = "brand.id", target = "brandId")
    Article toArticle(ArticleEntity articleEntity);

    @Mapping(source ="categories", target = "categoriesId", qualifiedByName = "toCategoryEntityListToIdList")
    List<ArticleToPrint> toArticleList(List<ArticleEntity> articleEntities);

    ArticleToPrint toArticleToPrint(ArticleEntity articleEntity);
}
