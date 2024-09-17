package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleIdNullException extends RuntimeException {
    public ArticleIdNullException() {
        super("Article id cannot be null or negative");
    }
}
