package bootcamp.stockmircoservice.infrastructure.output.jpa.adapter;

import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor

public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleRepository articleRepository;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public List<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        return articleEntityMapper.toArticleList(articleRepository.findAll());
    }
}
