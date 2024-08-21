package bootcamp.stockMircoservice.infrastructure.input.rest;

import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryRequest;
import bootcamp.stockMircoservice.adapters.driving.http.dto.CategoryResponse;
import bootcamp.stockMircoservice.adapters.driving.http.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {
    private final ICategoryHandler categoryHandler;

    @PostMapping
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryRequest categoryRequest){
        categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategory(){
        return ResponseEntity.ok(categoryHandler.getAllCategories());
    }
}
