package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandNameEmptyException extends RuntimeException{
    public BrandNameEmptyException() {
        super("The brand name is empty");
    }
}