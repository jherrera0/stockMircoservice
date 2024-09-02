package bootcamp.stockmircoservice.infrastructure.output.jpa.mapper;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.BrandEntity;
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
        BrandEntity BrandEntity = null;
        Brand brand = brandEntityMapper.toBrand(BrandEntity);
        assertNull(brand);
    }
}