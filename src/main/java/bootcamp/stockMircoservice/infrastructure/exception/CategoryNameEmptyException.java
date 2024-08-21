package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryNameEmptyException extends RuntimeException{
    public CategoryNameEmptyException() {
        super("Category name is empty");
    }
}