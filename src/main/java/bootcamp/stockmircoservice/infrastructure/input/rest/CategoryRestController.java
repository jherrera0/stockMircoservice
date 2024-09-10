package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.CategoryRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.ICategoryHandler;
import bootcamp.stockmircoservice.infrastructure.until.DocumentationConst;
import bootcamp.stockmircoservice.infrastructure.until.JwtConst;
import bootcamp.stockmircoservice.infrastructure.until.RuteConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing categories.
 */

@RestController
@RequestMapping(RuteConst.CATEGORY)
@RequiredArgsConstructor
@Tag(name = "Category", description = "API for managing categories")
public class CategoryRestController {
    private final ICategoryHandler categoryHandler;

    @Operation(summary = DocumentationConst.CATEGORY_SAVE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_201, description = DocumentationConst.CODE_201_DESCRIPTION_CATEGORY, content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_400, description = DocumentationConst.CODE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_409, description = DocumentationConst.CODE_409_DESCRIPTION_CATEGORY, content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_500, description = DocumentationConst.CODE_500_DESCRIPTION, content = @Content)
    })
    @PreAuthorize(JwtConst.HAS_AUTHORITY_ADMIN)
    @PostMapping(RuteConst.SAVE)
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = DocumentationConst.CATEGORY_ALL_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_201, description = DocumentationConst.CODE_201_DESCRIPTION_CATEGORY_ALL,
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))),
            @ApiResponse(responseCode = DocumentationConst.CODE_404, description = DocumentationConst.CODE_404_DESCRIPTION_CATEGORY_ALL, content = @Content)
    })
    @PreAuthorize(JwtConst.PERMIT_ALL)
    @GetMapping(RuteConst.ALL)
    public ResponseEntity<List<CategoryResponse>> getCategories(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortDirection){
        return ResponseEntity.ok(categoryHandler.getAllCategories(page, size, sortDirection));
    }
}
