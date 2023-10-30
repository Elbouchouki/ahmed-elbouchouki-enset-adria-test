package live.elbouchouki.core.dto.inventory;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record InventoryUpdateRequest(
        String name,
        @DecimalMin("0") BigDecimal price
) {
}
