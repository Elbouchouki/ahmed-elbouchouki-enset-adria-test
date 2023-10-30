package live.elbouchouki.core.dto.inventory;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InventoryCreateRequest(
        @NotBlank String name,
        @NotNull @DecimalMin("0") BigDecimal price
) {
}
