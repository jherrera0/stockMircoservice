package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException() {
        super("Brand already exists");
    }
}
