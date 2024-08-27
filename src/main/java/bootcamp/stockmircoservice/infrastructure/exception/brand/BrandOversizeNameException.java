package bootcamp.stockmircoservice.infrastructure.exception.brand;

public class BrandOversizeNameException extends RuntimeException{
    public BrandOversizeNameException() {
        super("The brand name is too long");
    }
}