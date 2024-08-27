package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandOversizeDescriptionException extends RuntimeException {
    public BrandOversizeDescriptionException() {
        super("The brand description is too long");
    }
}