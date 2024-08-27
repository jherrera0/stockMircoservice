package bootcamp.stockmircoservice.infrastructure.output.jpa.mapper;

import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IBrandEntityMapper {
    BrandEntity toBrandEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
}
