package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.VideographerDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideographerModel {

    public boolean saveVideographer(VideographerDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Videographer (videographer_id, name, contact, availability) VALUES (?, ?, ?, ?)",
                dto.getVideographerId(),
                dto.getName(),
                dto.getContact(),
                dto.getAvailability()
        );
    }

    public boolean updateVideographer(VideographerDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Videographer SET name = ?, contact = ?, availability = ? WHERE videographer_id = ?",
                dto.getName(),
                dto.getContact(),
                dto.getAvailability(),
                dto.getVideographerId()
        );
    }

    public boolean deleteVideographer(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Videographer WHERE videographer_id = ?", id);
    }

    public VideographerDto searchVideographer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Videographer WHERE videographer_id = ?", id);
        if (rs.next()) {
            return new VideographerDto(
                    rs.getString("videographer_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("availability")
            );
        }
        return null;
    }

    public ArrayList<VideographerDto> getAllVideographers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Videographer");
        ArrayList<VideographerDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new VideographerDto(
                    rs.getString("videographer_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("availability")
            ));
        }
        return list;
    }

    public String getNextVideographerId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT videographer_id FROM Videographer ORDER BY videographer_id DESC LIMIT 1");
        char prefix = 'V';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(1); // Remove prefix
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }

    public ArrayList<String> getAllVideographerIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT videographer_id FROM Videographer");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("videographer_id"));
        }
        return ids;
    }
}
