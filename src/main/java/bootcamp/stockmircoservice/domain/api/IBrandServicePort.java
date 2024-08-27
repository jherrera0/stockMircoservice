package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Brand;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
}
