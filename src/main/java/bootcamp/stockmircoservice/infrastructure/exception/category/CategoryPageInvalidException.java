package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryPageInvalidException extends RuntimeException {
    public CategoryPageInvalidException() {
        super("Category page cannot be negative or null");
    }
}
