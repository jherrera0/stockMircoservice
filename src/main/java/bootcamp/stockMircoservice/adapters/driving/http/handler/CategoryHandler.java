package bootcamp.stockMircoservice.adapters.driving.http.handler;

import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryRequest;
import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryResponse;
import bootcamp.stockMircoservice.adapters.driving.http.mapper.CategoryRequestMapper;
import bootcamp.stockMircoservice.adapters.driving.http.mapper.CategoryResponseMapper;
import bootcamp.stockMircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockMircoservice.domain.model.Category;
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
    public List<CategoryResponse> getAllCategories(Integer Page, Integer Size) {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategories(Page, Size));
    }
}
