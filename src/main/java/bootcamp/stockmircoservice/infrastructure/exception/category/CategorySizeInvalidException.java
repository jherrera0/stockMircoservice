package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategorySizeInvalidException extends RuntimeException {
    public CategorySizeInvalidException() {
        super("Category size cannot be negative or null");
    }
}
