package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandOversizeDescriptionException extends RuntimeException {
    public BrandOversizeDescriptionException() {
        super("Brand description is too long");
    }
}
