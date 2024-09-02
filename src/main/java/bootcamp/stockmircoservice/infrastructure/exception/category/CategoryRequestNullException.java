package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryRequestNullException extends RuntimeException {
    public CategoryRequestNullException() {
        super("Category request cannot be null");
    }
}
