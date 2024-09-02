package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticlePageEmptyException extends RuntimeException {
    public ArticlePageEmptyException() {
        super("Article page cannot be empty or less than 0");
    }
}
