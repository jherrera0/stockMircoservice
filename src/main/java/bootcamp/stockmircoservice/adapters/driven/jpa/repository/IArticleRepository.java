package bootcamp.stockmircoservice.adapters.driven.jpa.repository;

import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
