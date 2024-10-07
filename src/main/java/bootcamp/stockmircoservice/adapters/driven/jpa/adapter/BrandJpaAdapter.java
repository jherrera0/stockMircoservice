package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.BrandEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public PageCustom<Brand> getAllBrands(Integer page, Integer size, String sortDirection) {
        Pageable pagination;
        if (sortDirection == null || sortDirection.isEmpty()) {
            pagination = PageRequest.of(page, size);
        } else {
            pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), "name"));
        }
        Page<BrandEntity> brandPage = brandRepository.findAll(pagination);
        return new PageCustom<>(brandPage.getNumber(), brandPage.getSize(), brandPage.getTotalPages(), brandEntityMapper.toBrandList(brandPage.getContent()));
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByNameIgnoreCase(name).map(brandEntityMapper::toBrand);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id).map(brandEntityMapper::toBrand);
    }
}
