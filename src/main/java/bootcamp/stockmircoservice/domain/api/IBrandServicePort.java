package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.model.PageCustom;


public interface IBrandServicePort {
    void saveBrand(Brand brand);
    PageCustom<Brand> getAllBRands(Integer page, Integer size, String sortDirection);
    Brand findById(Long id);
}
