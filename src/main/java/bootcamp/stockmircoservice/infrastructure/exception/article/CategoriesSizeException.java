package bootcamp.stockmircoservice.infrastructure.exception.article;

public class CategoriesSizeException extends RuntimeException {
    public CategoriesSizeException() {
        super("The article must have between 1 and 3 categories");
    }
}
