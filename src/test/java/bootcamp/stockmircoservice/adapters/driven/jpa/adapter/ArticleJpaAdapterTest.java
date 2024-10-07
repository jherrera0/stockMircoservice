package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleJpaAdapterTest {

    @Mock
    private IArticleEntityMapper articleEntityMapper;

    @Mock
    private IArticleRepository articleRepository;

    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void saveArticle_ShouldSaveArticleSuccessfully() {
        Article article = new Article();
        when(articleEntityMapper.toArticleEntity(article)).thenReturn(new ArticleEntity());
        articleJpaAdapter.saveArticle(article);
        verify(articleRepository, times(1)).save(any(ArticleEntity.class));
    }
    @Test
    void getAllArticles_withValidParameters_returnsArticleList() {
        List<ArticleEntity> articleEntities = List.of(new ArticleEntity(), new ArticleEntity());
        when(articleRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(articleEntities));
        when(articleEntityMapper.toArticleList(articleEntities)).thenReturn(List.of(new ArticleToPrint(), new ArticleToPrint()));

        List<ArticleToPrint> result = articleJpaAdapter.getAllArticles(0, 10, "asc", "name");

        assertEquals(2, result.size());
    }

    @Test
    void getAllArticles_withEmptyResult_returnsEmptyList() {
        when(articleRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));
        when(articleEntityMapper.toArticleList(List.of())).thenReturn(List.of());

        List<ArticleToPrint> result = articleJpaAdapter.getAllArticles(0, 10, "asc", "name");

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllArticles_withInvalidSortDirection_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> articleJpaAdapter.getAllArticles(0, 10, "invalid", "name"));
    }

    @Test
    void getAllArticles_withNullPage_throwsException() {
        assertThrows(NullPointerException.class, () -> articleJpaAdapter.getAllArticles(null, 10, "asc", "name"));
    }

    @Test
    void getAllArticles_withNullSize_throwsException() {
        assertThrows(NullPointerException.class, () -> articleJpaAdapter.getAllArticles(0, null, "asc", "name"));
    }

    @Test
    void getAllArticles_withNullSortBy_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> articleJpaAdapter.getAllArticles(0, 10, "asc", null));
    }

    @Test
    void findById_withValidId_returnsArticle() {
        ArticleEntity articleEntity = new ArticleEntity();
        when(articleRepository.findById(1L)).thenReturn(Optional.of(articleEntity));
        when(articleEntityMapper.toArticle(articleEntity)).thenReturn(new Article());

        Article result = articleJpaAdapter.findById(1L);

        assertNotNull(result);
        verify(articleRepository, times(1)).findById(1L);
        verify(articleEntityMapper, times(1)).toArticle(articleEntity);
    }

    @Test
    void findById_withNonExistentId_throwsArticleNotFoundException() {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ArticleNotFoundException.class, () -> articleJpaAdapter.findById(1L));
        verify(articleRepository, times(1)).findById(1L);
    }

    @Test
    void updateArticle_withNonExistentArticle_throwsArticleNotFoundException() {
        Article article = new Article();
        article.setId(1L);
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ArticleNotFoundException.class, () -> articleJpaAdapter.updateArticle(article));
        verify(articleRepository, times(1)).findById(1L);
        verify(articleRepository, never()).updateByArticle(anyLong(), anyString(), anyString(), any(), anyLong(), any());
    }

    @Test
    void findArticleById_withValidId_returnsArticleToPrint() {
        ArticleEntity articleEntity = new ArticleEntity();
        when(articleRepository.findById(1L)).thenReturn(Optional.of(articleEntity));
        when(articleEntityMapper.toArticleToPrint(articleEntity)).thenReturn(new ArticleToPrint());

        ArticleToPrint result = articleJpaAdapter.findArticleById(1L);

        assertNotNull(result);
        verify(articleRepository, times(2)).findById(1L);
        verify(articleEntityMapper, times(1)).toArticleToPrint(articleEntity);
    }

    @Test
    void findArticleById_withNonExistentId_returnsNull() {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        ArticleToPrint result = articleJpaAdapter.findArticleById(1L);

        assertNull(result);
        verify(articleRepository, times(1)).findById(1L);
        verify(articleEntityMapper, never()).toArticleToPrint(any());
    }

    @Test
    void updateArticle_withValidArticle_updatesSuccessfully() {
        Article article = new Article();
        article.setId(1L);
        article.setName("Test Name");
        article.setDescription("Test Description");
        article.setPrice(BigDecimal.valueOf(10.0));
        article.setStock(100L);
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(1L);
        articleEntity.setName("Test Name");
        articleEntity.setDescription("Test Description");
        articleEntity.setPrice(BigDecimal.valueOf(10.0));
        articleEntity.setStock(100L);
        when(articleRepository.findById(1L)).thenReturn(Optional.of(articleEntity));

        articleJpaAdapter.updateArticle(article);

        verify(articleRepository, times(1)).findById(1L);
        verify(articleRepository, times(1)).updateByArticle(
                eq(1L), eq("Test Name"), eq("Test Description"), eq(BigDecimal.valueOf(10.0)), eq(100L), any()
        );
    }

}