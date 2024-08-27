package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.domain.model.Brand;
import bootcamp.stockmircoservice.infrastructure.exception.brand.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand/")
@RequiredArgsConstructor
@Tag(name = "brand", description = "API for managing brands")
public class BrandRestController {
    private final IBrandHandler brandHandler;

    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Brand already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Void> saveBrand(@RequestBody BrandRequest brandRequest) {
        if (brandRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            brandHandler.saveBrand(brandRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (BrandAlreadyExistsException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (BrandNameEmptyException | BrandDescriptionEmptyException | BrandOversizeNameException |
                 BrandOversizeDescriptionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    @Parameter(name = "page" , description = "Page number to retrieve (0-based)", example = "0")
    @Parameter(name = "size", description = "Number of items per page", example = "10")
    @Parameter(name = "sortDirection", description = "Sort direction (asc or desc)", example = "asc")
    public ResponseEntity<List<BrandResponse>> getBrands(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String sortDirection){
        try {
            if(brandHandler.getAllBrands(page, size, sortDirection).isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(brandHandler.getAllBrands(page, size, sortDirection));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}