package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryNullFieldException extends RuntimeException {
    public CategoryNullFieldException() {
        super("Category cannot be null or have empty fields");
    }
}
