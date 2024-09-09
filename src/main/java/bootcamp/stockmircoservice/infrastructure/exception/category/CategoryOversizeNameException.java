package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryOversizeNameException extends RuntimeException{
    public CategoryOversizeNameException() {
        super("The category name is too long");
    }
}