package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.adapters.driven.jpa.adapter.BrandJpaAdapter;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.BrandEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IBrandRepository;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
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
    void getAllBrands_ShouldReturnEmptyList_WhenNoBrandsExist() {
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        when(brandEntityMapper.toBrandList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<Brand> result = brandJpaAdapter.getAllBrands(0, 10, "asc");

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllBrands_ShouldReturnBrandsList_WhenBrandsExist() {
        List<BrandEntity> brandEntities = List.of(new BrandEntity(), new BrandEntity());
        List<Brand> brands = List.of(new Brand("Brand1", "Description1"), new Brand("Brand2", "Description2"));
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandEntities));
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(brands);

        List<Brand> result = brandJpaAdapter.getAllBrands(0, 10, ConstValuesToSort.ASCENDANT_SORT);

        assertEquals(2, result.size());
        assertEquals("Brand1", result.get(0).getName());
        assertEquals("Brand2", result.get(1).getName());
    }

    @Test
    void getAllBrands_ShouldReturnBrandsList_WhenSortDirectionIsNull() {
        List<BrandEntity> brandEntities = List.of(new BrandEntity(), new BrandEntity());
        List<Brand> brands = List.of(new Brand("Brand1", "Description1"), new Brand("Brand2", "Description2"));
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandEntities));
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(brands);

        List<Brand> result = brandJpaAdapter.getAllBrands(0, 10, null);

        assertEquals(2, result.size());
        assertEquals("Brand1", result.get(0).getName());
        assertEquals("Brand2", result.get(1).getName());
    }

    @Test
    void getAllBrands_ShouldReturnBrandsList_WhenSortDirectionIsEmpty() {
        List<BrandEntity> brandEntities = List.of(new BrandEntity(), new BrandEntity());
        List<Brand> brands = List.of(new Brand("Brand1", "Description1"), new Brand("Brand2", "Description2"));
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandEntities));
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(brands);

        List<Brand> result = brandJpaAdapter.getAllBrands(0, 10, "");

        assertEquals(2, result.size());
        assertEquals("Brand1", result.get(0).getName());
        assertEquals("Brand2", result.get(1).getName());
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

}