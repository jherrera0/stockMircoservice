package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleSortDirectionEmptyException extends RuntimeException {
    public ArticleSortDirectionEmptyException() {
        super("Article sort direction cannot be empty or null");
    }
}
