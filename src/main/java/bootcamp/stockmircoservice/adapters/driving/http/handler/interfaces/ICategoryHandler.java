package bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    PageCustomResponse<CategoryResponse> getAllCategories(Integer page, Integer size, String sortDirection);
}
