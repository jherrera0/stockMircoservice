package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;

import java.util.List;

public class BrandCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if(brand == null||brand.getName() == null||brand.getDescription() == null){
            throw new BrandNullFieldException();
        }
        if(brand.getName().isEmpty()||brand.getName().isBlank()){
            throw new BrandNameEmptyException();
        }
        if(brand.getName().length() > Brand.MAX_NAME_LENGTH){
            throw new BrandOversizeNameException();
        }
        if(brand.getDescription().isEmpty()||brand.getDescription().isBlank()){
            throw new BrandDescriptionEmptyException();
        }
        if(brand.getDescription().length() > Brand.MAX_DESCRIPTION_LENGTH){
            throw new BrandOversizeDescriptionException();
        }
        if (brandPersistencePort.findByName(brand.getName()).isPresent()) {
            throw new BrandAlreadyExistsException();
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBRands(Integer page, Integer size, String sortDirection) {
        return brandPersistencePort.getAllBrands(page, size, sortDirection);
    }
}
