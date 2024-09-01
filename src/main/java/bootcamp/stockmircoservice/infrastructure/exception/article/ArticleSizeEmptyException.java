package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleSizeEmptyException extends RuntimeException {
    public ArticleSizeEmptyException() {
        super("Article size cannot be empty or null");
    }
}
