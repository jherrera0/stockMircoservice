package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoriesNotFoundException extends RuntimeException {
    public CategoriesNotFoundException() {
        super("No se encontraron categorias");
    }
}