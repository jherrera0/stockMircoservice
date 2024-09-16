package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;

import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor

public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleRepository articleRepository;

    @Override
    @Transactional
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public List<ArticleToPrint> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        List<ArticleEntity> articleEntities = articleRepository.findAll(pageable).getContent();
        return articleEntityMapper.toArticleList(articleEntities);
    }

    @Override
    public Article findById(Long id) {
        ArticleEntity articleEntity = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException());
        return articleEntityMapper.toArticle(articleEntity);
    }

    @Override
    @Transactional
    public void updateArticle(Article article) {
        ArticleEntity articleEntity =  articleRepository.findById(article.getId()).orElseThrow(() -> new ArticleNotFoundException());
        articleEntity.setStock(article.getStock());
        articleRepository.updateByArticle(articleEntity.getId(), articleEntity.getName(), articleEntity.getDescription(),articleEntity.getPrice(), articleEntity.getStock(),articleEntity.getBrand());

    }


}
