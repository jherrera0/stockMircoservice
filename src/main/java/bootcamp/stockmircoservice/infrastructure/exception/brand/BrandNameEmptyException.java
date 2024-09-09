package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandNameEmptyException extends RuntimeException {
    public BrandNameEmptyException() {
        super("Brand name is empty");
    }
}
