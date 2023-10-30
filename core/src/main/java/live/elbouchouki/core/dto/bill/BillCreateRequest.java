package live.elbouchouki.core.dto.bill;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BillCreateRequest(
        @NotBlank String customerId,
        @NotNull List<@Valid ProductItemCreateRequest> productItems
) {
}
