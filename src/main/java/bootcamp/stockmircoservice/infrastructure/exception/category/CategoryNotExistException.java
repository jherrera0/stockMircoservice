package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryNotExistException extends RuntimeException {
    public CategoryNotExistException() {
        super("Category does not exist");
    }
}
