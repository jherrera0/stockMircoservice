package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor

public class BrandJpaAdapter implements IBrandPersistencePort {
    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;


    @Override
    public void saveBrand(Brand brand) {
        if (brand == null) {
            throw new IllegalArgumentException("Brand cannot be null");
        }
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }

    @Override
    public Optional<Brand> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return brandRepository.findByNameIgnoreCase(name).map(brandEntityMapper::toBrand);
    }
}
