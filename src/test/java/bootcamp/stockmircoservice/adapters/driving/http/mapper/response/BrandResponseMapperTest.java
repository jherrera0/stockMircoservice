package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrandResponseMapperTest {
    @InjectMocks
    BrandResponseMapper brandResponseMapper = new BrandResponseMapperImpl();
    @Test
    void toBrandResponse_ShouldMapEntityToResponseSuccessfully() {
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        BrandResponse brandResponse = brandResponseMapper.toResponse(brand);
        assertNotNull(brandResponse);
        assertEquals(brand.getId(), brandResponse.getId());
        assertEquals(brand.getName(), brandResponse.getName());
        assertEquals(brand.getDescription(), brandResponse.getDescription());
    }

    @Test
    void toBrandResponse_ShouldReturnNull_WhenEntityIsNull() {
        Brand brand = null;
        BrandResponse brandResponse = brandResponseMapper.toResponse(brand);
        assertNull(brandResponse);
    }

    @Test
    void toBrandResponseList_ShouldMapEntityListToResponseListSuccessfully() {
        List<Brand> brandEntities = List.of(
                new Brand(1L, "BrandName1", "BrandDescription1"),
                new Brand(2L, "BrandName2", "BrandDescription2")
        );
        List<BrandResponse> brandResponses = brandResponseMapper.toResponseList(brandEntities);
        assertNotNull(brandResponses);
        assertEquals(2, brandResponses.size());
        assertEquals(brandEntities.get(0).getId(), brandResponses.get(0).getId());
        assertEquals(brandEntities.get(1).getId(), brandResponses.get(1).getId());
    }

    @Test
    void toBrandResponseList_ShouldReturnEmptyList_WhenEntityListIsEmpty() {
        List<Brand> brands = List.of();
        List<BrandResponse> brandResponses = brandResponseMapper.toResponseList(brands);
        assertNotNull(brandResponses);
        assertTrue(brandResponses.isEmpty());
    }

}