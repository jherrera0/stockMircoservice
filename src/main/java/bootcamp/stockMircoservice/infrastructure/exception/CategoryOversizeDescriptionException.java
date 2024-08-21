package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryOversizeDescriptionException extends RuntimeException {
    public CategoryOversizeDescriptionException() {
        super("la descripcion proporcionada es muy grande (mas de 90 caracteres)");
    }
}