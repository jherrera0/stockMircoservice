package bootcamp.stockmircoservice.infrastructure.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("Category already exists");
    }
}