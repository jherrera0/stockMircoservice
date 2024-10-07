package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ArticleToCartResponse {
    private Long productId;
    private Long quantity;
    private String productName;
    private String brandName;
    private List<String> categories;
    private Double price;

    public ArticleToCartResponse(Long productId, Long quantity, String productName, String brandName, List<String> categories, Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.brandName = brandName;
        this.categories = categories;
        this.price = price;
    }

    public ArticleToCartResponse() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
