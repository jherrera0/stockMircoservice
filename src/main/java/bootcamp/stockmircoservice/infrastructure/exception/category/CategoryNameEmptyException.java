package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryNameEmptyException extends RuntimeException{
    public CategoryNameEmptyException() {
        super("The category name is empty");
    }
}