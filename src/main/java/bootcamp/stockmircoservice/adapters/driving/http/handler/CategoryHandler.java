package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.CategoryRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.CategoryResponseMapper;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories(Integer page, Integer size, String sortDirection) {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategories(page, size, sortDirection));
    }
}
