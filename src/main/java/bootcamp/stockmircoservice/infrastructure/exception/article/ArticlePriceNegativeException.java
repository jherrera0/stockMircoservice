package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticlePriceNegativeException extends RuntimeException {
    public ArticlePriceNegativeException() {
        super("Article price cannot be negative");
    }
}
