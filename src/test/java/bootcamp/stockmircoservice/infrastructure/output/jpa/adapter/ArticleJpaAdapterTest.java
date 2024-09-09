package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.adapters.driven.jpa.adapter.ArticleJpaAdapter;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void getAllArticles_ShouldReturnArticlesList() {
        when(articleRepository.findAll()).thenReturn(List.of(new ArticleEntity()));
        when(articleEntityMapper.toArticleList(anyList())).thenReturn(List.of(new Article()));
        List<Article> articles = articleJpaAdapter.getAllArticles(1, 10, "ASC", "name");
        assertNotNull(articles);
        assertFalse(articles.isEmpty());
    }
    @Test
    void getAllArticles_ShouldReturnEmptyList_WhenNoArticlesFound() {
        when(articleRepository.findAll()).thenReturn(List.of());
        when(articleEntityMapper.toArticleList(anyList())).thenReturn(List.of());
        List<Article> articles = articleJpaAdapter.getAllArticles(1, 10, "ASC", "name");
        assertNotNull(articles);
        assertTrue(articles.isEmpty());
    }
}