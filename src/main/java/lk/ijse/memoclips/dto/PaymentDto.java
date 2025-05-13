package lk.ijse.memoclips.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDto {
    private String paymentId;
    private String invoiceId;
    private String amountPaid;
    private String paymentDate;
    private String paymentMethod;
}
