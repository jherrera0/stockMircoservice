package bootcamp.stockMircoservice.adapters.driving.http.dto;

import lombok.Data;

@Data
public class CategoryResponse {
    private long id;
    private String name;
    private String description;
}
