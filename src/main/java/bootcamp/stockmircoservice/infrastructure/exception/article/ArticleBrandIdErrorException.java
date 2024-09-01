package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleBrandIdErrorException extends RuntimeException {
    public ArticleBrandIdErrorException() {
        super("Article brand id cannot be negative, zero or empty");
    }
}
