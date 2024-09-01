package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleCategoriesIdErrorException extends RuntimeException {
    public ArticleCategoriesIdErrorException() {
        super("Article categories id cannot be empty");
    }
}
