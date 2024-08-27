package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.BrandRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.BrandResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;

import bootcamp.stockmircoservice.domain.model.Brand;
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
        return brandResponseMapper.toResponseList(brandServicePort.getAllBRands(page, size, sortDirection));
    }
}
