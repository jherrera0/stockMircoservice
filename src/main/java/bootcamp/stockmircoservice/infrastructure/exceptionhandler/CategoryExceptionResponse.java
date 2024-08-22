package bootcamp.stockmircoservice.infrastructure.exceptionhandler;

public enum CategoryExceptionResponse {
    CATEGORY_NOT_FOUND("No found categories"),
    CATEGORY_ALREADY_EXISTS("There is already a pokemon with that number"),
    CATEGORY_OVERSIZE_DESCRIPTION("The description is too long"),
    CATEGORY_OVERSIZE_NAME("The name is too long"),
    CATEGORY_EMPTY_NAME("The name is empty"),
    CATEGORY_EMPTY_DESCRIPTION("The description is empty");
    private String message;

    CategoryExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
