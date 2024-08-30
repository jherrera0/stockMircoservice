package bootcamp.stockmircoservice.infrastructure.output.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Schema(description = "Details about the article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the article")
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Long stock;

    private Long brandId;

    @ElementCollection
    private List<Long> categoriesId;
}
