package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleSortByEmptyException extends RuntimeException {
    public ArticleSortByEmptyException() {
        super("Article sort by cannot be empty or null");
    }
}
