package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.BookingDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingModel {

    public boolean saveBooking(BookingDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Booking (booking_id, customer_id, date, time, location, event_type, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                dto.getBookingId(),
                dto.getCustomerId(),
                dto.getDate(),
                dto.getTime(),
                dto.getLocation(),
                dto.getBookingType(),
                dto.getBookingStatus()
        );
    }

    public boolean updateBooking(BookingDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Booking SET customer_id = ?, date = ?, time = ?, location = ?, event_type = ?, status = ? WHERE booking_id = ?",
                dto.getCustomerId(),
                dto.getDate(),
                dto.getTime(),
                dto.getLocation(),
                dto.getBookingType(),
                dto.getBookingStatus(),
                dto.getBookingId()
        );
    }

    public boolean deleteBooking(String bookingId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "DELETE FROM Booking WHERE booking_id = ?",
                bookingId
        );
    }

    public BookingDto searchBooking(String bookingId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM Booking WHERE booking_id = ?",
                bookingId
        );

        if (rs.next()) {
            return new BookingDto(
                    rs.getString("booking_id"),
                    rs.getString("customer_id"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("location"),
                    rs.getString("event_type"),
                    rs.getString("status")
            );
        }
        return null;
    }

    public ArrayList<BookingDto> getAllBookings() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Booking");
        ArrayList<BookingDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new BookingDto(
                    rs.getString("booking_id"),
                    rs.getString("customer_id"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("location"),
                    rs.getString("event_type"),
                    rs.getString("status")
            ));
        }

        return list;
    }

    public String getNextBookingId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT booking_id FROM Booking ORDER BY booking_id DESC LIMIT 1");
        char prefix = 'B';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(1);
            int number = Integer.parseInt(numberPart);
            String newId = String.format(prefix + "%03d", number + 1);
            return newId;
        }
        return prefix + "001";
    }

    public ArrayList<String> getAllBookingIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT booking_id FROM Booking");
        ArrayList<String> ids = new ArrayList<>();

        while (rs.next()) {
            ids.add(rs.getString(1));
        }
        return ids;
    }

    public ArrayList<BookingDto> getBookingsByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Booking WHERE customer_id = ?", customerId);
        ArrayList<BookingDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new BookingDto(
                    rs.getString("booking_id"),
                    rs.getString("customer_id"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("location"),
                    rs.getString("event_type"),
                    rs.getString("status")
            ));
        }
        return list;
    }
}
