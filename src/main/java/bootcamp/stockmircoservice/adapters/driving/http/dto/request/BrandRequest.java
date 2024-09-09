package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import lombok.Data;

@Data
public class BrandRequest {
    private long id;
    private String name;
    private String description;

    public BrandRequest(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public BrandRequest() {

    }

    public BrandRequest(BrandRequest brandRequest) {
        if (brandRequest == null) {
            throw new BrandNullFieldException();
        }
        setId(brandRequest.getId());
        setName(brandRequest.getName());
        setDescription(brandRequest.getDescription());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BrandNameEmptyException();
        }
        if (name.length() > ConstValues.BRAND_NAME_LENGTH_MAX) {
            throw new BrandOversizeNameException();
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new BrandDescriptionEmptyException();
        }
        if (description.length() > ConstValues.BRAND_DESCRIPTION_LENGTH_MAX) {
            throw new BrandOversizeDescriptionException();
        }
       this.description = description;
    }
}
