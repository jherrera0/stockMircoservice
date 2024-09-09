package bootcamp.stockmircoservice.infrastructure.configuration;

import bootcamp.stockmircoservice.adapters.driven.jpa.adapter.BrandJpaAdapter;
import bootcamp.stockmircoservice.adapters.driven.jpa.adapter.CategoryJpaAdapter;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IBrandRepository;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.ICategoryRepository;
import bootcamp.stockmircoservice.domain.api.IArticleServicePort;
import bootcamp.stockmircoservice.domain.spi.IArticlePersistencePort;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.domain.usecase.ArticleCase;
import bootcamp.stockmircoservice.adapters.driven.jpa.adapter.ArticleJpaAdapter;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@RequiredArgsConstructor

public class ArticleBeanConfiguration {
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;
    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public IArticlePersistencePort articlePersistence() {
        return new ArticleJpaAdapter(articleEntityMapper, articleRepository);
    }

    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleCase(articlePersistence(),brandPersistencePort(),categoryPersistencePort());
    }

    private ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryEntityMapper, categoryRepository);
    }

    private IBrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandEntityMapper, brandRepository);
    }

}

