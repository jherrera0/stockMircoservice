package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryNameEmptyException extends RuntimeException{
    public CategoryNameEmptyException() {
        super("The category name is empty");
    }
}