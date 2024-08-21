package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryDescriptionEmptyException extends  RuntimeException{
    public CategoryDescriptionEmptyException() {
        super("The category description is empty");
    }
}