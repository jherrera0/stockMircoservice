package bootcamp.stockmircoservice.infrastructure.until;

import bootcamp.stockmircoservice.adapters.driving.http.dto.response.ArticleResponse;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.BrandResponseMapper;
import bootcamp.stockmircoservice.adapters.driving.http.mapper.response.CategoryResponseMapper;
import bootcamp.stockmircoservice.domain.api.IBrandServicePort;
import bootcamp.stockmircoservice.domain.api.ICategoryServicePort;
import bootcamp.stockmircoservice.domain.model.Article;
import bootcamp.stockmircoservice.infrastructure.output.jpa.repository.ICategoryRepository;

import java.util.List;

public class GeneralMethods {
    public static List<ArticleResponse> MapArticleResponse(List<Article> articles, IBrandServicePort brandServicePort, ICategoryServicePort categoryServicePort, ICategoryRepository categoryRepository, BrandResponseMapper brandResponseMapper, CategoryResponseMapper categoryResponseMapper) {
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
            articleResponse.getCategories().forEach(categoryResponse -> categoryResponse.setId(categoryRepository.findByName(categoryResponse.getName()).getId()));
            return articleResponse;
        }).toList();
        return articleResponses;
    }
}
