package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticlePriceNullException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleNameEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleDescriptionEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleCategoriesIdErrorException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleIllegalStockValueException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticleBrandIdErrorException;
import bootcamp.stockmircoservice.infrastructure.exception.article.ArticlePriceNegativeException;
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

    public ArticleRequest(long id, String name, String description, Long brandId, List<Long> categoriesId, BigDecimal price, Long stock) {
        setId(id);
        setName(name);
        setDescription(description);
        setBrandId(brandId);
        setCategoriesId(categoriesId);
        setPrice(price);
        setStock(stock);
    }

    public ArticleRequest() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new ArticleNameEmptyException();
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new ArticleDescriptionEmptyException();
        }
        this.description = description;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        if (brandId==null || brandId < 0) {
            throw new ArticleBrandIdErrorException();
        }
        this.brandId = brandId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price == null){
            throw new ArticlePriceNullException();
        }
        if (price.compareTo(BigDecimal.ZERO) < ConstValues.ZERO) {
            throw new ArticlePriceNegativeException();
        }
        this.price = price;
    }

    public List<Long> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<Long> categoriesId) {
        if(categoriesId==null||categoriesId.isEmpty()){
            throw new ArticleCategoriesIdErrorException();
        }
        this.categoriesId = categoriesId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        if (stock == null||stock<ConstValues.ZERO) {
            throw new ArticleIllegalStockValueException();
        }
        this.stock = stock;
    }
}
