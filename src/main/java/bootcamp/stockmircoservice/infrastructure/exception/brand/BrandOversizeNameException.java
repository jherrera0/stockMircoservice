package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandOversizeNameException extends RuntimeException {
    public BrandOversizeNameException() {
        super("Brand name is too long");
    }
}
