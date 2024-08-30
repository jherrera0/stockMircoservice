package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/article/")
@RequiredArgsConstructor
@Tag(name = "article", description = "API for managing articles")
public class ArticleRestController {
    private final IArticleHandler articleHandler;
    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "article created", content = @Content),
            @ApiResponse(responseCode = "409", description = "article already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Void> saveBrand(@RequestBody ArticleRequest articleRequest) {
        articleHandler.saveArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleResponse>> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        return ResponseEntity.ok(articleHandler.getAllArticles(page, size, sortDirection, sortBy));
    }
}
