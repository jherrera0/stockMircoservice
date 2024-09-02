package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleDescriptionEmptyException extends RuntimeException {
    public ArticleDescriptionEmptyException() {
        super("Article description cannot be empty");
    }
}
