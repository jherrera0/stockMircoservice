package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.ICategoryHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.CategoryRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.IPageCustomResponseMapper;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryRequestNullException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final CategoryRequestMapper categoryRequestMapper;
    private final IPageCustomResponseMapper pageCustomResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        if(categoryRequest == null){
            throw new CategoryRequestNullException();
        }
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public PageCustomResponse<CategoryResponse> getAllCategories(Integer page, Integer size, String sortDirection) {
        return pageCustomResponseMapper.toResponsePageOfCategory(categoryServicePort.getAllCategories(page, size, sortDirection));
    }
}
