package live.elbouchouki.core.dto.bill;

import com.fasterxml.jackson.annotation.JsonInclude;
import live.elbouchouki.core.dto.inventory.InventoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductItemResponse {
    private String id;
    private Integer quantity;
    private String productId;
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InventoryResponse product;
}
