package bootcamp.stockmircoservice.domain.usecase;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandNotFoundException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;

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
        if(page == null || page< ConstValuesToPage.ZERO){
            throw new BrandPageInvalidException();
        }
        if(size == null || size < ConstValuesToPage.ZERO){
            throw new BrandSizeInvalidException();
        }
        if(sortDirection == null || sortDirection.isEmpty()){
            throw new BrandSortDirectionEmptyException();
        }
        if(!sortDirection.equals(ConstValuesToSort.ASCENDANT_SORT) && (!sortDirection.equals(ConstValuesToSort.DESCENDANT_SORT))){
            throw new BrandSortDirectionInvalidException();
        }
        return brandPersistencePort.getAllBrands(page, size, sortDirection);
    }

    @Override
    public Brand findById(Long id) {
        return brandPersistencePort.findById(id).orElseThrow(BrandNotFoundException::new);
    }
}
