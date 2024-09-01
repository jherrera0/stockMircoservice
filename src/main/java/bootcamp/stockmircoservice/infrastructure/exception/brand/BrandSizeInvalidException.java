package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandSizeInvalidException extends RuntimeException {
    public BrandSizeInvalidException() {
        super("Brand size is negative or null");
    }
}
