package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SupplierIdDto {
    private String supplierId;
    private String name;
    private String contact;
    private String supplyQuantity;
}
