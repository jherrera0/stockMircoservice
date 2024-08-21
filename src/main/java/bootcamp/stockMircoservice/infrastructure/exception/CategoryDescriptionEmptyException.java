package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryDescriptionEmptyException extends  RuntimeException{
    public CategoryDescriptionEmptyException() {
        super("La descripción de la categoría no puede estar vacía");
    }
}