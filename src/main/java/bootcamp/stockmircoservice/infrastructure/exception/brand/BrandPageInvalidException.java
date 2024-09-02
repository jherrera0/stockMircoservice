package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandPageInvalidException extends RuntimeException {
    public BrandPageInvalidException() {
        super("Brand page cannot be less than 1 or null");
    }
}
