package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDto {
    private String customerId;
    private String customerName;
    private String contactNo;
    private String address;
    private String email;
    private String adminId;
}
