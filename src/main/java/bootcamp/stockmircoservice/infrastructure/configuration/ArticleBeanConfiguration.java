package bootcamp.stockmircoservice.infrastructure.configuration;

import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.domain.usecase.ArticleCase;
import bootcamp.stockmircoservice.infrastructure.output.jpa.adapter.ArticleJpaAdapter;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@RequiredArgsConstructor

public class ArticleBeanConfiguration {
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;

    @Bean
    public IArticlePersistencePort articlePersistence() {
        return new ArticleJpaAdapter(articleEntityMapper, articleRepository);
    }

    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleCase(articlePersistence());
    }

}

