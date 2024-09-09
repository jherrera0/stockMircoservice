package bootcamp.stockmircoservice.infrastructure.configuration;

import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.domain.usecase.CategoryCase;
import bootcamp.stockmircoservice.adapters.driven.jpa.adapter.CategoryJpaAdapter;
import bootcamp.stockmircoservice.adapters.driven.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockmircoservice.adapters.driven.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@RequiredArgsConstructor
public class CategoryBeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistence() {
        return new CategoryJpaAdapter(categoryEntityMapper, categoryRepository);
    }
    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryCase(categoryPersistence());
    }

}
