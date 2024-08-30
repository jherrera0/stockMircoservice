package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryRequestNegativeException extends RuntimeException {
    public CategoryRequestNegativeException() {
        super("The page and size must be positive");
    }
}
