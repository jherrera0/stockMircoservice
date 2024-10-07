package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.model.PageCustom;

import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    PageCustom<Brand> getAllBrands(Integer page, Integer size, String sortDirection);
    Optional<Brand> findByName(String name);
    Optional<Brand> findById(Long id);
}
