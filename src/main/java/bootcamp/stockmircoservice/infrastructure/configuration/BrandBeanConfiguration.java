package bootcamp.stockmircoservice.infrastructure.configuration;

import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.spi.IBrandPersistencePort;
import bootcamp.stockmircoservice.domain.usecase.BrandCase;
import bootcamp.stockmircoservice.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@RequiredArgsConstructor

public class BrandBeanConfiguration {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public IBrandPersistencePort brandPersistence(){
        return new BrandJpaAdapter(brandEntityMapper,brandRepository);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandCase(brandPersistence());
    }

}
