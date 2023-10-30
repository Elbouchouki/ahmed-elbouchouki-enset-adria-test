package live.elbouchouki.core.dto.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import live.elbouchouki.core.dto.wallet.WalletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferResponse {
    private String id;
    private double balance;
    private String currency;
    private LocalDate createdDate;

    private WalletResponse source;
    private WalletResponse destination;


//    Customer Response from customer-service which it doesn't exist yet
//    private CustomerResponse customer;
}
