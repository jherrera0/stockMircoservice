package bootcamp.stockmircoservice.infrastructure.output.jpa.repository;

import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByNameIgnoreCase(String name);
    CategoryEntity findByName(String name);
}