package bootcamp.stockmircoservice.infrastructure.exception;

public class CategoriesNotFoundException extends RuntimeException {
    public CategoriesNotFoundException() {
        super("No found categories");
    }
}