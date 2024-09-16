package bootcamp.stockmircoservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SupplyRequest {
    @Positive
    @NotNull
    private Long productId;
    @Positive
    @NotNull
    private Long quantity;
}

