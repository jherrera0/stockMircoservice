package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleSortByInvalidException extends RuntimeException {
    public ArticleSortByInvalidException() {
        super("Article sort by invalid, sort by must be one of the following: name, brand, categories.name");
    }
}
