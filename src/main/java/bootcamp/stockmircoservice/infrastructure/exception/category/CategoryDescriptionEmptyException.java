package bootcamp.stockmircoservice.infrastructure.exception.category;

public class CategoryDescriptionEmptyException extends  RuntimeException{
    public CategoryDescriptionEmptyException() {
        super("The category description is empty");
    }
}