package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import lombok.Data;

@Data
public class CategoryResponse {
    private long id;
    private String name;
    private String description;
}
