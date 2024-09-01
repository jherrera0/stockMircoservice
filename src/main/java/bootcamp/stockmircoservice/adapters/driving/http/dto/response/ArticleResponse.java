package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ArticleResponse {
    private long id;
    private String name;
    private String description;
    private BrandResponse brand;
    private List<CategoryResponse> categories;
    private BigDecimal price;
    private Long stock;

    public void setBrand(BrandResponse brand) {
        this.brand = brand;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }
}
