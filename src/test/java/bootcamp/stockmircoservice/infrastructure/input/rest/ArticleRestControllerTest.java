package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.request.SupplyRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleToCartResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.ArticleHandler;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleNotFoundException;
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
        assertDoesNotThrow(() -> articleRestController.saveArticle(articleRequest));
    }
    @Test
    void updateArticleStock_ShouldUpdateSuccessfully_WhenValidParameters() {
        SupplyRequest supplyRequest = new SupplyRequest();
        supplyRequest.setProductId(1L);
        supplyRequest.setQuantity(10L);
        doNothing().when(articleHandler).updateArticle(1L, 10L);
        assertDoesNotThrow(() -> articleRestController.updateArticleStock(supplyRequest));
    }

    @Test
    void updateArticleStock_ShouldThrowException_WhenInvalidProductId() {
        SupplyRequest supplyRequest = new SupplyRequest();
        supplyRequest.setProductId(-1L);
        supplyRequest.setQuantity(10L);
        doThrow(new IllegalArgumentException("Invalid product ID")).when(articleHandler).updateArticle(-1L, 10L);
        assertThrows(IllegalArgumentException.class, () -> articleRestController.updateArticleStock(supplyRequest));
    }

    @Test
    void updateArticleStock_ShouldThrowException_WhenInvalidQuantity() {
        SupplyRequest supplyRequest = new SupplyRequest();
        supplyRequest.setProductId(1L);
        supplyRequest.setQuantity(-10L);
        doThrow(new IllegalArgumentException("Invalid quantity")).when(articleHandler).updateArticle(1L, -10L);
        assertThrows(IllegalArgumentException.class, () -> articleRestController.updateArticleStock(supplyRequest));
    }

    @Test
    void getArticle_withValidId_returnsArticleToCartResponse() {
        ArticleToCartResponse expectedResponse = new ArticleToCartResponse();
        when(articleHandler.getArticleById(1L)).thenReturn(expectedResponse);

        ResponseEntity<ArticleToCartResponse> response = articleRestController.getArticle(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getArticle_withNonExistentId_throwsArticleNotFoundException() {
        when(articleHandler.getArticleById(1L)).thenThrow(new ArticleNotFoundException());

        assertThrows(ArticleNotFoundException.class, () -> articleRestController.getArticle(1L));
    }


    @Test
    void getAllArticles_withValidParameters_returnsArticleList() {
        List<ArticleResponse> expectedArticles = List.of(new ArticleResponse());
        when(articleHandler.getAllArticles(1, 10, "ASC", "name")).thenReturn(expectedArticles);

        ResponseEntity<List<ArticleResponse>> response = articleRestController.getAllArticles(1, 10, "ASC", "name");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedArticles, response.getBody());
    }

    @Test
    void getAllArticles_withNoArticlesFound_returnsEmptyList() {
        when(articleHandler.getAllArticles(1, 10, "ASC", "name")).thenReturn(List.of());

        ResponseEntity<List<ArticleResponse>> response = articleRestController.getAllArticles(1, 10, "ASC", "name");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }
}