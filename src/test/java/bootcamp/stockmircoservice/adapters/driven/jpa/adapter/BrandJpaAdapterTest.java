package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.BrandEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IBrandRepository;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandJpaAdapterTest {

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @Mock
    private IBrandRepository brandRepository;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_ShouldSaveBrandSuccessfully() {
        Brand brand = new Brand("Test Brand", "Test Description");
        BrandEntity brandEntity = new BrandEntity();
        when(brandEntityMapper.toBrandEntity(brand)).thenReturn(brandEntity);

        brandJpaAdapter.saveBrand(brand);

        verify(brandRepository, times(1)).save(brandEntity);
    }


    @Test
    void findByName_ShouldReturnBrand_WhenBrandExists() {
        String brandName = "Test Brand";
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(brandName);
        when(brandRepository.findByNameIgnoreCase(brandName)).thenReturn(Optional.of(brandEntity));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(new Brand(brandName, "Test Description"));

        Optional<Brand> result = brandJpaAdapter.findByName(brandName);

        assertTrue(result.isPresent());
        assertEquals(brandName, result.get().getName());
    }

    @Test
    void findByName_ShouldReturnEmpty_WhenBrandDoesNotExist() {
        String brandName = "NonExistent Brand";
        when(brandRepository.findByNameIgnoreCase(brandName)).thenReturn(Optional.empty());

        Optional<Brand> result = brandJpaAdapter.findByName(brandName);

        assertFalse(result.isPresent());
    }

    @Test
    void findById_ShouldReturnBrand_WhenBrandExists() {
        Long brandId = 1L;
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brandEntity));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(new Brand(brandId,"Test Description","Test Brand"));

        Optional<Brand> result = brandJpaAdapter.findById(brandId);

        assertTrue(result.isPresent());
        assertEquals(brandId, result.get().getId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenBrandDoesNotExist() {
        Long brandId = 1L;
        when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        Optional<Brand> result = brandJpaAdapter.findById(brandId);

        assertFalse(result.isPresent());
    }
    @Test
    void getAllBrands_withValidParameters_returnsBrandList() {
        List<BrandEntity> brandEntities = List.of(new BrandEntity(), new BrandEntity());
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandEntities));
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(List.of(new Brand(), new Brand()));

        PageCustom<Brand> result = brandJpaAdapter.getAllBrands(0, 10, "asc");

        assertEquals(2, result.getItems().size());
    }

    @Test
    void getAllBrands_withEmptyResult_returnsEmptyList() {
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));
        when(brandEntityMapper.toBrandList(List.of())).thenReturn(List.of());

        PageCustom<Brand> result = brandJpaAdapter.getAllBrands(0, 10, "asc");

        assertTrue(result.getItems().isEmpty());
    }

    @Test
    void getAllBrands_withNullSortDirection_returnsUnsortedList() {
        List<BrandEntity> brandEntities = List.of(new BrandEntity(), new BrandEntity());
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandEntities));
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(List.of(new Brand(), new Brand()));

        PageCustom<Brand> result = brandJpaAdapter.getAllBrands(0, 10, null);

        assertEquals(2, result.getItems().size());
    }

}