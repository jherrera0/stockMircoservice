package bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    PageCustomResponse<BrandResponse> getAllBrands(Integer page, Integer size, String sortDirection);
}
