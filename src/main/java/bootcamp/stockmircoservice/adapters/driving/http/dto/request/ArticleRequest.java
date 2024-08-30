package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ArticleRequest {
    private long id;
    private String name;
    private String description;
    private Long brandId;
    private List<Long> categoriesId;
    private BigDecimal price;
    private Long stock;
}
