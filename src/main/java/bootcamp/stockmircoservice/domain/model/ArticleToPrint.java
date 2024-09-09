package bootcamp.stockmircoservice.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class ArticleToPrint {
    private Long id;
    private String name;
    private String description;
    private Long stock;
    private BigDecimal price;
    private Brand brand;
    private List<Category> categories;


    public ArticleToPrint(Long id, String name, String description, Long stock, BigDecimal price, Brand brand, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }

    public ArticleToPrint() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
