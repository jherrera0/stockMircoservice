package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.BrandEntity;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

public class BrandJpaAdapter implements IBrandPersistencePort {
    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;


    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }

    @Override
    public List<Brand> getAllBrands(Integer page, Integer size, String sortDirection) {
        Pageable pagination;
        if (sortDirection == null || sortDirection.isEmpty()) {
            pagination = PageRequest.of(page, size);
        } else {
            pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), "name"));
        }
        List<BrandEntity> categories = brandRepository.findAll(pagination).getContent();
        return brandEntityMapper.toBrandList(categories);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByNameIgnoreCase(name).map(brandEntityMapper::toBrand);
    }
}
