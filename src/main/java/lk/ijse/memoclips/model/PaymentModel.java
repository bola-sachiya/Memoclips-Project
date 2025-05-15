package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.PaymentDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {

    public boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Payment (payment_id, invoice_id, amount_paid, payment_date, payment_method) VALUES (?, ?, ?, ?, ?)",
                dto.getPaymentId(),
                dto.getInvoiceId(),
                dto.getAmountPaid(),
                dto.getPaymentDate(),
                dto.getPaymentMethod()
        );
    }

    public boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Payment SET invoice_id = ?, amount_paid = ?, payment_date = ?, payment_method = ? WHERE payment_id = ?",
                dto.getInvoiceId(),
                dto.getAmountPaid(),
                dto.getPaymentDate(),
                dto.getPaymentMethod(),
                dto.getPaymentId()
        );
    }

    public boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Payment WHERE payment_id = ?", paymentId);
    }

    public PaymentDto searchPayment(String paymentId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Payment WHERE payment_id = ?", paymentId);
        if (rs.next()) {
            return new PaymentDto(
                    rs.getString("payment_id"),
                    rs.getString("invoice_id"),
                    rs.getString("amount_paid"),
                    rs.getString("payment_date"),
                    rs.getString("payment_method")
            );
        }
        return null;
    }

    public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Payment");
        ArrayList<PaymentDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PaymentDto(
                    rs.getString("payment_id"),
                    rs.getString("invoice_id"),
                    rs.getString("amount_paid"),
                    rs.getString("payment_date"),
                    rs.getString("payment_method")
            ));
        }
        return list;
    }

    public String getNextPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT payment_id FROM Payment ORDER BY payment_id DESC LIMIT 1");
        char prefix = 'P';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(1);
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

    public ArrayList<String> getAllPaymentIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT payment_id FROM Payment");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString(1));
        }
        return ids;
    }
}
