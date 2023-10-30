package live.elbouchouki.core.dto.wallet;

public record WalletUpdateRequest(
        String currency,
        double balance
) {
}
