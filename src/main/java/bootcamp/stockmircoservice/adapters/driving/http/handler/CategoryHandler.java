package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.ICategoryHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.CategoryRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.CategoryResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.until.ConstValuesToPage;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategoryPageInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategorySizeInvalidException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategorySortDirectionEmptyException;
import bootcamp.stockmircoservice.infrastructure.exception.category.CategorySortDirectionInvalidException;
import bootcamp.stockmircoservice.infrastructure.until.ConstValuesToSort;
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
        if(page == null || page< ConstValuesToPage.ZERO){
            throw new CategoryPageInvalidException();
        }
        if(size == null || size < ConstValuesToPage.ZERO){
            throw new CategorySizeInvalidException();
        }
        if(sortDirection == null || sortDirection.isEmpty()){
            throw new CategorySortDirectionEmptyException();
        }
        if(!sortDirection.equals(ConstValuesToSort.ASCENDANT_SORT) && (!sortDirection.equals(ConstValuesToSort.DESCENDANT_SORT))){
                throw new CategorySortDirectionInvalidException();
        }

        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategories(page, size, sortDirection));
    }
}
