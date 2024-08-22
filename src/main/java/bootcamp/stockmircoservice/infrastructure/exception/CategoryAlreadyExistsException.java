package bootcamp.stockmircoservice.infrastructure.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("There is already a category with that name");
    }
}