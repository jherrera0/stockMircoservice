package bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;

import java.util.List;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    List<BrandResponse> getAllBrands(Integer page, Integer size, String sortDirection);
}
