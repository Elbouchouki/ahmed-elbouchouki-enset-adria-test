package live.elbouchouki.core.dto.bill;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ProductItemUpdateRequest(
        @NotNull @DecimalMin("1") Integer quantity
) {
}
