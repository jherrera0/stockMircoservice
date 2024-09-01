package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleSortDirectionInvalidException extends RuntimeException {
    public ArticleSortDirectionInvalidException() {
        super("Article sort direction is invalid, sort direction must be one of the following: asc, desc");
    }
}
