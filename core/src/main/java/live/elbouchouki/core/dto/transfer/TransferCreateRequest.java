package live.elbouchouki.core.dto.transfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransferCreateRequest(
        @NotNull Integer customerId,
        @NotBlank String currency,
        Double balance // initial balance
) {
}
