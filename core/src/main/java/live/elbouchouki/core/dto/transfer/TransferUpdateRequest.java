package live.elbouchouki.core.dto.transfer;

import live.elbouchouki.core.enums.TransferStatus;

public record TransferUpdateRequest(
        TransferStatus status
) {
}
