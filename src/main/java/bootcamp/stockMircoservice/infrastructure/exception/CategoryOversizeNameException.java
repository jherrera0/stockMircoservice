package bootcamp.stockMircoservice.infrastructure.exception;

public class CategoryOversizeNameException extends RuntimeException{
    public CategoryOversizeNameException() {
        super("el nombre es muy largo (mas de 50 caracteres) ");
    }
}