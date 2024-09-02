package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleIllegalStockValueException extends RuntimeException {
    public ArticleIllegalStockValueException() {
        super("Article stock cannot be negative or null");
    }
}
