package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ArticleResponse {
    private long id;
    private String name;
    private String description;
    private BrandResponseCustom brand;
    private List<CategoryResponseCustom> categories;
    private BigDecimal price;
    private Long stock;

}
