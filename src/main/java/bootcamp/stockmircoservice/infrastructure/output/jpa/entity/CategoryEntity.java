package bootcamp.stockmircoservice.infrastructure.output.jpa.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Details about the Category")

public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the category")
    private Long id;

    @Schema(description = "The name of the category", example = "Electronics", maxLength = 50)
    private String name;

    @Schema(description = "A brief description of the category", example = "Category for all electronic items", maxLength = 90)
    private String description;

}

