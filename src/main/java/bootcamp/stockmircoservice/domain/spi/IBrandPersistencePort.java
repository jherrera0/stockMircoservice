package bootcamp.stockmircoservice.domain.spi;

import bootcamp.stockmircoservice.domain.model.Brand;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
}
