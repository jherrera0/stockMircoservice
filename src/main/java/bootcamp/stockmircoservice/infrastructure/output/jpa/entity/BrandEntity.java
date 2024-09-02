package bootcamp.stockmircoservice.infrastructure.output.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Details about the brand")

public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the brand")
    private Long id;

    @Schema(description = "The name of the category", example = "Emazon", maxLength = 50)
    private String name;

    @Schema(description = "A brief description of the category", example = "Emazon brand", maxLength = 120)
    private String description;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<ArticleEntity> articleEntities;
}
