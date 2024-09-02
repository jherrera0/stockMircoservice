package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import lombok.Data;

@Data
public class BrandResponse {
    private long id;
    private String name;
    private String description;

    public BrandResponse(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public BrandResponse() {
    }
}
