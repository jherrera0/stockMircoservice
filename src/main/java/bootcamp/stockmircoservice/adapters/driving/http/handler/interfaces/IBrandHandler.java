package bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
}
