package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class ItemDto {
    private String itemId;
    private String itemName;
    private String quantity;
    private String lastUpdateDate;
    private String supplierId;
}
