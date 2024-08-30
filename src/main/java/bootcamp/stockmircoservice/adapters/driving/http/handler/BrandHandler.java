package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.BrandRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
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
    private final IBrandPersistencePort brandPersistencePort;

    @Override
    public void saveBrand(BrandRequest brandRequest) {

        if(brandRequest == null||brandRequest.getName() == null||brandRequest.getDescription() == null){
            throw new BrandNullFieldException();
        }
        if(brandRequest.getName().isEmpty()||brandRequest.getName().isBlank()){
            throw new BrandNameEmptyException();
        }
        if(brandRequest.getName().length() > Brand.MAX_NAME_LENGTH){
            throw new BrandOversizeNameException();
        }
        if(brandRequest.getDescription().isEmpty()||brandRequest.getDescription().isBlank()){
            throw new BrandDescriptionEmptyException();
        }
        if(brandRequest.getDescription().length() > Brand.MAX_DESCRIPTION_LENGTH){
            throw new BrandOversizeDescriptionException();
        }
        if (brandPersistencePort.findByName(brandRequest.getName()).isPresent()) {
            throw new BrandAlreadyExistsException();
        }
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public List<BrandResponse> getAllBrands(Integer page, Integer size, String sortDirection) {
        if((page == null) || (page < 0) || (size == null) || (size < 0)){
            throw new BrandRequestNegativeException();
        }
        return brandResponseMapper.toResponseList(brandServicePort.getAllBRands(page, size, sortDirection));
    }
}
