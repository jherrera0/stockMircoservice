package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("Ya existe una categoría con el nombre");
    }
}