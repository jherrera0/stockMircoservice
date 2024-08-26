package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;

public class BrandCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brandPersistencePort.findByName(brand.getName()).isPresent()) {
            throw new BrandAlreadyExistsException();
        }
        if(brand.getName().isEmpty()){
            throw new BrandNameEmptyException();
        }
        if(brand.getName().length() > Brand.MAX_NAME_LENGTH){
            throw new BrandOversizeNameException();
        }
        if(brand.getDescription().isEmpty()){
            throw new BrandDescriptionEmptyException();
        }
        if(brand.getDescription().length() > Brand.MAX_DESCRIPTION_LENGTH){
            throw new BrandOversizeDescriptionException();
        }
        brandPersistencePort.saveBrand(brand)
        ;
    }
}
