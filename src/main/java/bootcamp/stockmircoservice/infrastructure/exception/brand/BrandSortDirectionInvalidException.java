package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandSortDirectionInvalidException extends RuntimeException {
    public BrandSortDirectionInvalidException() {
        super("Brand sort direction is invalid or null");
    }
}
