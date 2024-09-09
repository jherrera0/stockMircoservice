package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValues;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNullFieldException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryNameEmptyException;
import lombok.Data;


@Data
public class CategoryRequest {
    private long id;
    private String name;
    private String description;

    public CategoryRequest(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public CategoryRequest() {

    }

    public CategoryRequest(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            throw new CategoryNullFieldException();
        }
        setId(categoryRequest.getId());
        setName(categoryRequest.getName());
        setDescription(categoryRequest.getDescription());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new CategoryNameEmptyException();
        }
        if (name.length() > ConstValues.CATEGORY_NAME_LENGTH_MAX) {
            throw new CategoryNameEmptyException();
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new CategoryNameEmptyException();
        }
        if (description.length() > ConstValues.CATEGORY_DESCRIPTION_LENGTH_MAX) {
            throw new CategoryNameEmptyException();
        }
        this.description = description;
    }
}
