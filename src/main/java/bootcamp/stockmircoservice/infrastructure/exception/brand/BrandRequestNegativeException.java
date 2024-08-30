package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandRequestNegativeException extends RuntimeException {
    public BrandRequestNegativeException() {
        super("The page and size must be positive");
    }
}
