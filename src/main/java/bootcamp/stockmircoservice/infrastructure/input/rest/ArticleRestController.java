package bootcamp.stockmircoservice.infrastructure.input.rest;

import bootcamp.stockmircoservice.adapters.driving.http.dto.request.ArticleRequest;
import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.handler.ArticleHandler;
import bootcamp.stockmircoservice.adapters.driving.http.handler.interfaces.IArticleHandler;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.CategoryResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.domain.spi.ICategoryPersistencePort;
import bootcamp.stockmircoservice.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.IArticleRepository;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.ICategoryRepository;
import bootcamp.stockmircoservice.infrastructure.until.Validation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final IArticleRepository articleRepository;
    private final ICategoryRepository categoryRepository;
    private final IArticleEntityMapper articleEntityMapper;
    private final BrandResponseMapper brandResponseMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;
    private final IBrandServicePort brandServicePort;
    private final ICategoryPersistencePort categoryPersistencePort;


    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "article created", content = @Content),
            @ApiResponse(responseCode = "409", description = "article already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Void> saveBrand(@RequestBody ArticleRequest articleRequest) {
        Validation.validationSaveArticle(articleRequest, categoryPersistencePort);
        articleHandler.saveArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleResponse>> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        Validation.validationGetAllArticles(page, size, sortDirection, sortBy);
        Pageable pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        List<Article> articles = articleEntityMapper.toArticleList(articleRepository.findAll(pagination).getContent());
        List<ArticleResponse> articleResponses = articles.stream().map(article -> {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article.getId());
            articleResponse.setName(article.getName());
            articleResponse.setDescription(article.getDescription());
            articleResponse.setPrice(article.getPrice());
            articleResponse.setStock(article.getStock());
            articleResponse.setBrand(brandResponseMapper.toResponse(brandServicePort.findById(article.getBrandId())));
            articleResponse.getBrand().setId(brandServicePort.findById(article.getBrandId()).getId());
            articleResponse.setCategories(categoryResponseMapper.toResponseList(categoryServicePort.findByArticleId(article.getId())));
            articleResponse.getCategories().forEach(categoryResponse -> {
                categoryResponse.setId(categoryRepository.findByName(categoryResponse.getName()).getId());
            });
            return articleResponse;
        }).toList();
        return ResponseEntity.ok(articleResponses);
    }
}