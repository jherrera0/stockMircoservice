package bootcamp.stockmircoservice.infrastructure.output.jpa.mapper;

import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IBrandEntityMapperTest {

    @InjectMocks
    IBrandEntityMapper brandEntityMapper = new IBrandEntityMapperImpl();

    @Test
    void toBrandEntity_ShouldMapBrandToEntitySuccessfully() {
        Brand brand = new Brand(1L, "BrandName","BrandDescription");
        BrandEntity brandEntity = brandEntityMapper.toBrandEntity(brand);
        assertNotNull(brandEntity);
        assertEquals(brand.getId(), brandEntity.getId());
        assertEquals(brand.getName(), brandEntity.getName());
    }

    @Test
    void toBrandEntity_ShouldReturnNull_WhenBrandIsNull() {
        BrandEntity brandEntity = brandEntityMapper.toBrandEntity(null);
        assertNull(brandEntity);
    }

    @Test
    void toBrand_ShouldMapEntityToBrandSuccessfully() {
        BrandEntity brandEntity = new BrandEntity(1L, "BrandName","BrandDescription", List.of(new ArticleEntity()));
        Brand brand = brandEntityMapper.toBrand(brandEntity);
        assertNotNull(brand);
        assertEquals(brandEntity.getId(), brand.getId());
        assertEquals(brandEntity.getName(), brand.getName());
    }

    @Test
    void toBrand_ShouldReturnNull_WhenBrandEntityIsNull() {
        BrandEntity brandEntity = null;
        Brand brand = brandEntityMapper.toBrand(brandEntity);
        assertNull(brand);
    }
    @Test
    void toBrandList_ShouldMapEntityListToBrandListSuccessfully() {
        List<BrandEntity> brandEntities = List.of(
                new BrandEntity(1L, "BrandName1", "BrandDescription1", List.of(new ArticleEntity())),
                new BrandEntity(2L, "BrandName2", "BrandDescription2", List.of(new ArticleEntity()))
        );
        List<Brand> brands = brandEntityMapper.toBrandList(brandEntities);
        assertNotNull(brands);
        assertEquals(2, brands.size());
        assertEquals(brandEntities.get(0).getId(), brands.get(0).getId());
        assertEquals(brandEntities.get(1).getId(), brands.get(1).getId());
    }

    @Test
    void toBrandList_ShouldReturnEmptyList_WhenEntityListIsEmpty() {
        List<BrandEntity> brandEntities = List.of();
        List<Brand> brands = brandEntityMapper.toBrandList(brandEntities);
        assertNotNull(brands);
        assertTrue(brands.isEmpty());
    }

    @Test
    void toBrandEntityNamed_ShouldMapIdToBrandEntitySuccessfully() {
        Long brandId = 1L;
        BrandEntity brandEntity = brandEntityMapper.toBrand(brandId);
        assertNotNull(brandEntity);
        assertEquals(brandId, brandEntity.getId());
    }

    @Test
    void toBrandEntityNamed_ShouldReturnNull_WhenIdIsNull() {
        Long brandId = null;
        BrandEntity brandEntity = brandEntityMapper.toBrand(brandId);
        assertNull(brandEntity.getId());
    }
}