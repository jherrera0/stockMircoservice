package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleQuantityNullException extends RuntimeException {
    public ArticleQuantityNullException() {
        super("Article quantity cannot be null or negative");
    }
}
