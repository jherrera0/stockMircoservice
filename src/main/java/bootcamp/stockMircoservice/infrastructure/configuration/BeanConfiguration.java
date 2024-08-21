package bootcamp.stockMircoservice.infrastructure.configuration;

import bootcamp.stockMircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockMircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockMircoservice.domain.usecase.CategoryCase;
import bootcamp.stockMircoservice.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import bootcamp.stockMircoservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import bootcamp.stockMircoservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@RequiredArgsConstructor
public class BeanConfiguration {
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
