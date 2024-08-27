package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Brand;

import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Optional<Brand> findByName(String name);
}
