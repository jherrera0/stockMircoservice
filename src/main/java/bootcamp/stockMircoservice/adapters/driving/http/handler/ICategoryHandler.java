package bootcamp.stockMircoservice.adapters.driving.http.handler;

import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryRequest;
import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategories(Integer page, Integer size, String sortDirection);
}
