package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticlePriceNullException extends RuntimeException {
    public ArticlePriceNullException() {
        super("Article price cannot be null");
    }
}
