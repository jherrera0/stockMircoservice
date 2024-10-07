package bootcamp.stockmircoservice.adapters.driving.http.mapper.response;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleToCartResponse;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IArticleToCartResponseMapperTest {
    @Mock
    private IArticleToCartResponseMapper articleToCartResponseMapper;

    @BeforeEach
    void setUp() {
        articleToCartResponseMapper = new IArticleToCartResponseMapperImpl();
    }

    @Test
    void toArticleToCartResponse_withValidArticleToPrint_returnsArticleToCartResponse() {
        ArticleToPrint articleToPrint = new ArticleToPrint();
        articleToPrint.setId(1L);
        articleToPrint.setName("Test Product");
        articleToPrint.setBrand(new Brand("Test Brand",""));
        articleToPrint.setCategories(List.of(new Category("Category1",""), new Category("Category2","")));
        articleToPrint.setStock(10L);
        articleToPrint.setPrice(BigDecimal.valueOf(100));

        ArticleToCartResponse response = articleToCartResponseMapper.toArticleToCartResponse(articleToPrint);

        assertEquals(1L, response.getProductId());
        assertEquals("Test Product", response.getProductName());
        assertEquals("Test Brand", response.getBrandName());
        assertEquals(List.of("Category1", "Category2"), response.getCategories());
        assertEquals(10L, response.getQuantity());
        assertEquals(100.0, response.getPrice());
    }

    @Test
    void toArticleToCartResponse_withNullBrand_returnsArticleToCartResponseWithNullBrandName() {
        ArticleToPrint articleToPrint = new ArticleToPrint();
        articleToPrint.setId(1L);
        articleToPrint.setName("Test Product");
        articleToPrint.setCategories(List.of(new Category("Category1",""), new Category("Category2","")));
        articleToPrint.setStock(10L);
        articleToPrint.setPrice(BigDecimal.valueOf(100));

        ArticleToCartResponse response = articleToCartResponseMapper.toArticleToCartResponse(articleToPrint);

        assertEquals(1L, response.getProductId());
        assertEquals("Test Product", response.getProductName());
        assertNull(response.getBrandName());
        assertEquals(List.of("Category1", "Category2"), response.getCategories());
        assertEquals(10L, response.getQuantity());
        assertEquals(100.0, response.getPrice());
    }

    @Test
    void toArticleToCartResponse_withEmptyCategories_returnsArticleToCartResponseWithEmptyCategories() {
        ArticleToPrint articleToPrint = new ArticleToPrint();
        articleToPrint.setId(1L);
        articleToPrint.setName("Test Product");
        articleToPrint.setBrand(new Brand("Test Brand",""));
        articleToPrint.setCategories(Collections.emptyList());
        articleToPrint.setStock(10L);
        articleToPrint.setPrice(BigDecimal.valueOf(100));

        ArticleToCartResponse response = articleToCartResponseMapper.toArticleToCartResponse(articleToPrint);

        assertEquals(1L, response.getProductId());
        assertEquals("Test Product", response.getProductName());
        assertEquals("Test Brand", response.getBrandName());
        assertTrue(response.getCategories().isEmpty());
        assertEquals(10L, response.getQuantity());
        assertEquals(100.0, response.getPrice());
    }

    @Test
    void mapCategories_withValidCategories_returnsCategoryNames() {
        List<Category> categories = List.of(new Category("Category1",""), new Category("Category2",""));

        List<String> categoryNames = articleToCartResponseMapper.mapCategories(categories);

        assertEquals(List.of("Category1", "Category2"), categoryNames);
    }

    @Test
    void mapCategories_withEmptyCategories_returnsEmptyList() {
        List<Category> categories = Collections.emptyList();

        List<String> categoryNames = articleToCartResponseMapper.mapCategories(categories);

        assertTrue(categoryNames.isEmpty());
    }
}