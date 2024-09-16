package bootcamp.stockmircoservice.infrastructure.exception.article;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException() {
        super("Article not found");
    }
}
