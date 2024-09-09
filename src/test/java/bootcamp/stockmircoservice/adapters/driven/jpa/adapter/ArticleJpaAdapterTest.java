package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;

import bootcamp.stockmircoservice.adapters.driven.jpa.adapter.ArticleJpaAdapter;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
}