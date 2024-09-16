package bootcamp.stockmircoservice.adapters.driven.jpa.repository;

import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ArticleEntity a SET a.name = :name, a.description = :description, a.price = :price, "
            + "a.stock = :stock, a.brand = :brand WHERE a.id = :id")
    void updateByArticle(@Param("id") Long id,
                         @Param("name") String name,
                         @Param("description") String description,
                         @Param("price") BigDecimal price,
                         @Param("stock") Long stock,
                         @Param("brand") BrandEntity brand);
}
