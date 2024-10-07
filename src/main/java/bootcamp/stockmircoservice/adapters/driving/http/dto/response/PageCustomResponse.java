package bootcamp.stockmircoservice.adapters.driving.http.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PageCustomResponse<T> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private List<T> items;

    public PageCustomResponse(Integer currentPage, Integer pageSize, Integer totalPages, List<T> items) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.items = items;
    }

    public PageCustomResponse() {
    }
}
