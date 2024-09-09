package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.ArticleHandler;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.article.*;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNotExistException;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ArticleRestControllerTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private ArticleHandler articleHandler;

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private IArticleEntityMapper articleEntityMapper;


    @InjectMocks
    private ArticleRestController articleRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllArticles_ShouldReturnEmptyList_WhenNoArticlesFound() {
        when(articleRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));
        ResponseEntity<List<ArticleResponse>> response = articleRestController.getAllArticles(1, 10, ConstValuesToSort.ASCENDANT_SORT, ConstValuesToSort.SORT_BY_NAME);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void saveArticle_ShouldSaveSuccessfully_WhenBrandIdIsPositive() {
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setCategoriesId(List.of(1L));
        articleRequest.setBrandId(1L);
        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(new Category()));
        doNothing().when(articleHandler).saveArticle(articleRequest);
        assertDoesNotThrow(() -> articleHandler.saveArticle(articleRequest));
    }
    @Test
    void saveArticle_ShouldSaveSuccessfully_WhenValidParameters() {
        ArticleRequest articleRequest = new ArticleRequest("name", "description", 1L, List.of(1L), BigDecimal.ONE, 1L);
        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(new Category()));
        doNothing().when(articleHandler).saveArticle(articleRequest);
        assertDoesNotThrow(() -> articleRestController.saveBrand(articleRequest));
    }

}