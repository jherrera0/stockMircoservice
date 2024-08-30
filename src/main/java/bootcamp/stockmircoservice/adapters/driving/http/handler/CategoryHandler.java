package bootcamp.stockmircoservice.adapters.driving.http.handler;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.ICategoryHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.request.CategoryRequestMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.CategoryResponseMapper;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Category;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.exception.category.*;
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
    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        if(categoryRequest.getName().isEmpty()||categoryRequest.getName().isBlank()){
            throw new CategoryNameEmptyException();
        }
        if(categoryRequest.getName().length() > Category.MAX_NAME_LENGTH){
            throw new CategoryOversizeNameException();
        }
        if(categoryRequest.getDescription().isEmpty()||categoryRequest.getDescription().isBlank()){
            throw new CategoryDescriptionEmptyException();
        }
        if(categoryRequest.getDescription().length() > Category.MAX_DESCRIPTION_LENGTH){
            throw new CategoryOversizeDescriptionException();
        }
        if (categoryPersistencePort.findByName(categoryRequest.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories(Integer page, Integer size, String sortDirection) {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategories(page, size, sortDirection));
    }
}
