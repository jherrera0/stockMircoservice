package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.BrandRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.BrandResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.CategoryResponse;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.PageCustomResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IBrandHandler;
import bootcamp.stockmircoservice.infrastructure.until.DocumentationConst;
import bootcamp.stockmircoservice.infrastructure.until.JwtConst;
import bootcamp.stockmircoservice.infrastructure.until.RuteConst;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping(RuteConst.BRAND)
@RequiredArgsConstructor
@Tag(name = "brand", description = "API for managing brands")
public class BrandRestController {
    private final IBrandHandler brandHandler;

    @Operation(summary = DocumentationConst.BRAND_SAVE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_201, description = DocumentationConst.CODE_201_DESCRIPTION_BRAND, content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_409, description = DocumentationConst.CODE_409_DESCRIPTION_BRAND, content = @Content)
    })
    @PreAuthorize(JwtConst.HAS_AUTHORITY_ADMIN)
    @PostMapping(RuteConst.SAVE)
    public ResponseEntity<Void> saveBrand(@RequestBody BrandRequest brandRequest) {
            brandHandler.saveBrand(brandRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = DocumentationConst.BRAND_ALL_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_201, description = DocumentationConst.CODE_201_DESCRIPTION_BRAND_ALL,
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))),
            @ApiResponse(responseCode = DocumentationConst.CODE_404, description = DocumentationConst.CODE_404_DESCRIPTION_BRAND_ALL, content = @Content)
    })
    @PreAuthorize(JwtConst.PERMIT_ALL)
    @GetMapping(RuteConst.ALL)
    public ResponseEntity<PageCustomResponse<BrandResponse>> getBrands(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortDirection){
        return ResponseEntity.ok(brandHandler.getAllBrands(page, size, sortDirection));
    }
}