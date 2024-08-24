package bootcamp.stockmircoservice.infrastructure.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String string) {
        super(string);
    }
}