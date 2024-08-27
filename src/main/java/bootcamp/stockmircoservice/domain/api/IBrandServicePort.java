package bootcamp.stockmircoservice.domain.api;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.model.Category;

import java.util.List;

import java.util.List;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    List<Brand> getAllBRands(Integer page, Integer size, String sortDirection);
}
