package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleRequestNullException extends RuntimeException {
    public ArticleRequestNullException() {
        super("Article request cannot be null");
    }
}
