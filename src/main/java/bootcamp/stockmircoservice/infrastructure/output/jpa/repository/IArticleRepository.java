package bootcamp.stockmircoservice.infrastructure.output.jpa.repository;

import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
