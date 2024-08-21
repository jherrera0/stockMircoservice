package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryOversizeDescriptionException extends RuntimeException {
    public CategoryOversizeDescriptionException() {
        super("The category description is too long");
    }
}