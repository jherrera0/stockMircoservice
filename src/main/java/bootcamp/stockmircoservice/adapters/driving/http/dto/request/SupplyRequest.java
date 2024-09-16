package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import lombok.Data;

@Data
public class SupplyRequest {
    private Long productId;
    private Long quantity;
}

