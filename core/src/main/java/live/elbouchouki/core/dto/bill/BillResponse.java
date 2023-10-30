package live.elbouchouki.core.dto.bill;

import com.fasterxml.jackson.annotation.JsonInclude;
import live.elbouchouki.core.dto.customer.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponse {
    private String id;
    private Date billingDate;
    private String customerId;
    private CustomerResponse customer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProductItemResponse> productItems;
}
