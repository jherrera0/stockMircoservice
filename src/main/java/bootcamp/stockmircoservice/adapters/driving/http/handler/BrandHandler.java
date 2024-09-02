package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.BrandRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandPageInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandSizeInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandSortDirectionInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.brand.BrandSortDirectionEmptyException;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandHandler implements IBrandHandler {

    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;
    private final IBrandServicePort brandServicePort;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public List<BrandResponse> getAllBrands(Integer page, Integer size, String sortDirection) {
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
        return brandResponseMapper.toResponseList(brandServicePort.getAllBRands(page, size, sortDirection));
    }
}
