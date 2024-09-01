package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException() {
        super("Brand not found");
    }
}
