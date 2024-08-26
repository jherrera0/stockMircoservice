package bootcamp.stockmircoservice.infrastructure.output.jpa.repository;

import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByNameIgnoreCase(String name);
}
