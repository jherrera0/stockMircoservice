package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategorySortDirectionInvalidException extends RuntimeException {
    public CategorySortDirectionInvalidException() {
        super("Category sort direction is invalid, sort direction must be one of the following: asc, desc");
    }
}
