package live.elbouchouki.core.dto.bill;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BillUpdateRequest(
        List<ProductItemCreateRequest> productItems
) {
}
