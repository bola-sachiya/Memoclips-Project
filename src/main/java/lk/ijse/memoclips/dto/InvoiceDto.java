package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class InvoiceDto {
    private String invoiceId;
    private String bookingId;
    private String amount;
    private String dueDate;
    private String PaymentStatus;
}
