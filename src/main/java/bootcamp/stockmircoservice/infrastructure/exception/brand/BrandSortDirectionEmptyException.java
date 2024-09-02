package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandSortDirectionEmptyException extends RuntimeException {
    public BrandSortDirectionEmptyException() {
        super("Brand sort direction cannot be empty or null");
    }
}
