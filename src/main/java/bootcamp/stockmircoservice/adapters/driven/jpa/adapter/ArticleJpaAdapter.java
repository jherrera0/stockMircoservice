package bootcamp.stockmircoservice.adapters.driven.jpa.adapter;

import bootcamp.stockmircoservice.adapters.driven.jpa.entity.ArticleEntity;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.model.ArticleToPrint;
import bootcamp.stockmircoservice.domain.model.PageCustom;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

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
    public PageCustom<ArticleToPrint> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        Page<ArticleEntity> articlePage = articleRepository.findAll(pageable);
        return new PageCustom<>(articlePage.getNumber(), articlePage.getSize(), articlePage.getTotalPages(), articleEntityMapper.toArticleList(articlePage.getContent()));
    }

    @Override
    public Article findById(Long id) {
        ArticleEntity articleEntity = articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        return articleEntityMapper.toArticle(articleEntity);
    }

    @Override
    @Transactional
    public void updateArticle(Article article) {
        ArticleEntity articleEntity =  articleRepository.findById(article.getId()).orElseThrow(ArticleNotFoundException::new);
        articleEntity.setStock(article.getStock());
        articleRepository.updateByArticle(articleEntity.getId(), articleEntity.getName(), articleEntity.getDescription(),articleEntity.getPrice(), articleEntity.getStock(),articleEntity.getBrand());

    }

    @Override
    public ArticleToPrint findArticleById(Long id) {
        if (articleRepository.findById(id).isPresent()) {
            return articleEntityMapper.toArticleToPrint(articleRepository.findById(id).get());
        }
        else {
            return null;
        }
    }
}
