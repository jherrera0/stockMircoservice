package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("Category already exists");
    }
}