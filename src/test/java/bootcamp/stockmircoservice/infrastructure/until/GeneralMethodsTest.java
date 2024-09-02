package bootcamp.stockmircoservice.infrastructure.until;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.CategoryResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GeneralMethodsTest {

    @Test
    void mapArticleResponse_ShouldReturnEmptyList_WhenNoArticlesProvided() {
        List<Article> articles = List.of();
        IBrandServicePort brandServicePort = mock(IBrandServicePort.class);
        ICategoryServicePort categoryServicePort = mock(ICategoryServicePort.class);
        ICategoryRepository categoryRepository = mock(ICategoryRepository.class);
        BrandResponseMapper brandResponseMapper = mock(BrandResponseMapper.class);
        CategoryResponseMapper categoryResponseMapper = mock(CategoryResponseMapper.class);

        List<ArticleResponse> articleResponses = GeneralMethods.mapArticleResponse(articles, brandServicePort, categoryServicePort, categoryRepository, brandResponseMapper, categoryResponseMapper);

        assertNotNull(articleResponses);
        assertTrue(articleResponses.isEmpty());
    }

    @Test
    void mapArticleResponse_ShouldHandleNullCategoriesGracefully() {
        List<Article> articles = List.of(new Article(1L, "Article1", "Description1", 1L ,BigDecimal.valueOf(100), 1L, List.of(1L)));
        IBrandServicePort brandServicePort = mock(IBrandServicePort.class);
        ICategoryServicePort categoryServicePort = mock(ICategoryServicePort.class);
        ICategoryRepository categoryRepository = mock(ICategoryRepository.class);
        BrandResponseMapper brandResponseMapper = mock(BrandResponseMapper.class);
        CategoryResponseMapper categoryResponseMapper = mock(CategoryResponseMapper.class);

        Brand brand = new Brand();
        brand.setId(1L);
        when(brandServicePort.findById(1L)).thenReturn(brand);
        when(brandResponseMapper.toResponse(brand)).thenReturn(new BrandResponse());

        when(categoryServicePort.findByArticleId(1L)).thenReturn(null);
        when(categoryResponseMapper.toResponseList(anyList())).thenReturn(null);

        List<ArticleResponse> articleResponses = GeneralMethods.mapArticleResponse(articles, brandServicePort, categoryServicePort, categoryRepository, brandResponseMapper, categoryResponseMapper);

        assertNotNull(articleResponses);
        assertFalse(articleResponses.isEmpty());
        assertNotNull(articleResponses.get(0).getBrand());
        assertEquals("[]",articleResponses.get(0).getCategories().toString());
    }
}
