package bootcamp.stockMircoservice.adapters.driving.http.dto;

import lombok.Data;


@Data
public class CategoryRequest {
    private long id;
    private String name;
    private String description;

}
