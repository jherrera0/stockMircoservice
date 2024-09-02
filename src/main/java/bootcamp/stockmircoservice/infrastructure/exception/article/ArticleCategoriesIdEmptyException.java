package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleCategoriesIdEmptyException extends RuntimeException {
    public ArticleCategoriesIdEmptyException() {
        super("Article categories id cannot be empty");
    }
}
