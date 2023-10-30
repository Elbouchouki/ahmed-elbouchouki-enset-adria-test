package live.elbouchouki.core.dto.wallet.customer;

public record WalletUpdateRequest(
        String currency,
        double balance
) {
}
