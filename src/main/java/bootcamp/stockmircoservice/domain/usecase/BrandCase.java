package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;

public class BrandCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        brandPersistencePort.saveBrand(brand);
    }
}
