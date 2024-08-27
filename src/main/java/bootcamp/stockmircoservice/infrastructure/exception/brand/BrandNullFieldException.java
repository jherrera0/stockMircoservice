package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandNullFieldException extends RuntimeException {
    public BrandNullFieldException() {
        super("Brand name and description cannot be null");
    }
}
