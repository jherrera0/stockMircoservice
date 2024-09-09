package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandDescriptionEmptyException extends RuntimeException {
    public BrandDescriptionEmptyException() {
        super("Brand description is empty");
    }
}
