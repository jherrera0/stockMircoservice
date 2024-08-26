package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class BrandJpaAdapter implements IBrandPersistencePort {
    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;

    @Override
    public void saveBrand(Brand brand) {
    brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }
}
