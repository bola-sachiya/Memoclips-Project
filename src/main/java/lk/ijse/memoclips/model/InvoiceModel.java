package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.InvoiceDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceModel {

    public boolean saveInvoice(InvoiceDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Invoice (invoice_id, booking_id, amount, due_date, payment_status) VALUES (?, ?, ?, ?, ?)",
                dto.getInvoiceId(),
                dto.getBookingId(),
                dto.getAmount(),
                dto.getDueDate(),
                dto.getPaymentStatus()
        );
    }

    public boolean updateInvoice(InvoiceDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Invoice SET booking_id = ?, amount = ?, due_date = ?, payment_status = ? WHERE invoice_id = ?",
                dto.getBookingId(),
                dto.getAmount(),
                dto.getDueDate(),
                dto.getPaymentStatus(),
                dto.getInvoiceId()
        );
    }

    public boolean deleteInvoice(String invoiceId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Invoice WHERE invoice_id = ?", invoiceId);
    }

    public InvoiceDto searchInvoice(String invoiceId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Invoice WHERE invoice_id = ?", invoiceId);
        if (rs.next()) {
            return new InvoiceDto(
                    rs.getString("invoice_id"),
                    rs.getString("booking_id"),
                    rs.getString("amount"),
                    rs.getString("due_date"),
                    rs.getString("payment_status")
            );
        }
        return null;
    }

    public ArrayList<InvoiceDto> getAllInvoices() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Invoice");
        ArrayList<InvoiceDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new InvoiceDto(
                    rs.getString("invoice_id"),
                    rs.getString("booking_id"),
                    rs.getString("amount"),
                    rs.getString("due_date"),
                    rs.getString("payment_status")
            ));
        }

        return list;
    }

    public String getNextInvoiceId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT invoice_id FROM Invoice ORDER BY invoice_id DESC LIMIT 1");
        char prefix = 'I';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(1);
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

    public ArrayList<String> getAllInvoiceIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT invoice_id FROM Invoice");
        ArrayList<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add(rs.getString(1));
        }

        return list;
    }

    public ArrayList<InvoiceDto> getInvoicesByBookingId(String bookingId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Invoice WHERE booking_id = ?", bookingId);
        ArrayList<InvoiceDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new InvoiceDto(
                    rs.getString("invoice_id"),
                    rs.getString("booking_id"),
                    rs.getString("amount"),
                    rs.getString("due_date"),
                    rs.getString("payment_status")
            ));
        }

        return list;
    }
}
