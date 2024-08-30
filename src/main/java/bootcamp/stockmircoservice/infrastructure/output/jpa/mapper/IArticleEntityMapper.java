package bootcamp.stockmircoservice.infrastructure.output.jpa.mapper;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IArticleEntityMapper {
    ArticleEntity toArticleEntity(Article article);

    List<Article> toArticleList(List<ArticleEntity> articleEntities);
}
