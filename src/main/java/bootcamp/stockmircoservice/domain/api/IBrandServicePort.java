package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Brand;

import java.util.List;
import java.util.Optional;


public interface IBrandServicePort {
    void saveBrand(Brand brand);
    List<Brand> getAllBRands(Integer page, Integer size, String sortDirection);
    Brand findById(Long id);
}
