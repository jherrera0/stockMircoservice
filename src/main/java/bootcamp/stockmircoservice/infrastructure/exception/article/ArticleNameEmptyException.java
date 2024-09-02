package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleNameEmptyException extends RuntimeException {
    public ArticleNameEmptyException() {
        super("Article name cannot be empty");
    }
}
