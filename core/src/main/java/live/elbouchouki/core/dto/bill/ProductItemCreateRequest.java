package live.elbouchouki.core.dto.bill;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductItemCreateRequest(
        @NotNull @DecimalMin("1") Integer quantity,
        @NotBlank String productId
) {
}
