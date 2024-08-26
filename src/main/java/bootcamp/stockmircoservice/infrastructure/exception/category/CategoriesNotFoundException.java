package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoriesNotFoundException extends RuntimeException {
    public CategoriesNotFoundException() {
        super("No found categories");
    }
}