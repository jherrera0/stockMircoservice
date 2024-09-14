package bootcamp.stockmircoservice.infrastructure.exception.jwt;

public class MalFormJwtException extends RuntimeException {
    public MalFormJwtException() {
        super("Malformed JWT");
    }
}
