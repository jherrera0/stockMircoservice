package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandNullFieldException extends RuntimeException {
    public BrandNullFieldException() {
        super("Brand cannot be null or have null fields");
    }
}
