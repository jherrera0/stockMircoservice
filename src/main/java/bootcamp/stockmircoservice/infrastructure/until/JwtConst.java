package bootcamp.stockmircoservice.infrastructure.until;

public class JwtConst {
    public static final String HAS_AUTHORITY_AUX_WAREHOUSE = "hasAuthority('AUX_WAREHOUSE')";
    public static final String HAS_AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER ="Bearer";
    public static final Integer SUB_STRING_INDEX = 7;
    public static final String PERMIT_ALL = "permitAll()";

    private JwtConst() {
    }
}
