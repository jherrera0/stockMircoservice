package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategorySortDirectionEmptyException extends RuntimeException {
    public CategorySortDirectionEmptyException() {
        super("Category sort direction cannot be empty or null");
    }
}
