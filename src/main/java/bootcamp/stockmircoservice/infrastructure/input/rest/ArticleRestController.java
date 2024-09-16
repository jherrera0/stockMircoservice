package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.request.SupplyRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import bootcamp.stockmircoservice.infrastructure.until.DocumentationConst;
import bootcamp.stockmircoservice.infrastructure.until.JwtConst;
import bootcamp.stockmircoservice.infrastructure.until.RuteConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RuteConst.ARTICLE)
@RequiredArgsConstructor
@Tag(name = "article", description = "API for managing articles")
public class ArticleRestController {
    private final IArticleHandler articleHandler;

    @Operation(summary = DocumentationConst.ARTICLE_SAVE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_201, description = DocumentationConst.CODE_201_DESCRIPTION_ARTICLE, content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_409, description = DocumentationConst.CODE_409_DESCRIPTION_ARTICLE, content = @Content)
    })
    @PreAuthorize(JwtConst.HAS_AUTHORITY_ADMIN)
    @PostMapping(RuteConst.SAVE)
    public ResponseEntity<Void> saveArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.saveArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = DocumentationConst.ARTICLE_ALL_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_201, description = DocumentationConst.CODE_201_DESCRIPTION_ARTICLES_ALL,content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_404, description = DocumentationConst.CODE_404_DESCRIPTION_ARTICLES_ALL, content = @Content)
    })
    @PreAuthorize(JwtConst.PERMIT_ALL)
    @GetMapping(RuteConst.ALL)
    public ResponseEntity<List<ArticleResponse>> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        List<ArticleResponse> articleResponses = articleHandler.getAllArticles(page, size, sortDirection, sortBy);
        return ResponseEntity.ok(articleResponses);
    }

    @PreAuthorize(JwtConst.HAS_AUTHORITY_AUX_WAREHOUSE)
    @PutMapping(RuteConst.UPDATE)
    public ResponseEntity<Void> updateArticleStock(@RequestBody SupplyRequest supplyRequest) {
        articleHandler.updateArticle(supplyRequest.getProductId(), supplyRequest.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}