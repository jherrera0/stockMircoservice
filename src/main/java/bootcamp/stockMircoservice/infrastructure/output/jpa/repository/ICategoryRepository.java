package bootcamp.stockMircoservice.infrastructure.output.jpa.repository;

import bootcamp.stockMircoservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String categoryName);
    Page<CategoryEntity> findAllByOrderByNameAsc(Pageable pageable);
    Page<CategoryEntity> findAllByOrderByNameDesc(Pageable pageable);
}
