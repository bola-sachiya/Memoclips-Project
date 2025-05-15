package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.PhotographerDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotographerModel {

    public boolean savePhotographer(PhotographerDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Photographer (photographer_id, name, specialty, contact, availability) VALUES (?, ?, ?, ?, ?)",
                dto.getPhotographerId(),
                dto.getName(),
                dto.getSpeciality(),
                dto.getContact(),
                dto.getAvailability()
        );
    }

    public boolean updatePhotographer(PhotographerDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Photographer SET name = ?, specialty = ?, contact = ?, availability = ? WHERE photographer_id = ?",
                dto.getName(),
                dto.getSpeciality(),
                dto.getContact(),
                dto.getAvailability(),
                dto.getPhotographerId()
        );
    }

    public boolean deletePhotographer(String photographerId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Photographer WHERE photographer_id = ?", photographerId);
    }

    public PhotographerDto searchPhotographer(String photographerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Photographer WHERE photographer_id = ?", photographerId);
        if (rs.next()) {
            return new PhotographerDto(
                    rs.getString("photographer_id"),
                    rs.getString("name"),
                    rs.getString("specialty"),
                    rs.getString("contact"),
                    rs.getString("availability")
            );
        }
        return null;
    }

    public ArrayList<PhotographerDto> getAllPhotographers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Photographer");
        ArrayList<PhotographerDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotographerDto(
                    rs.getString("photographer_id"),
                    rs.getString("name"),
                    rs.getString("specialty"),
                    rs.getString("contact"),
                    rs.getString("availability")
            ));
        }
        return list;
    }

    public String getNextPhotographerId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT photographer_id FROM Photographer ORDER BY photographer_id DESC LIMIT 1");
        char prefix = 'P';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(1);
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

    public ArrayList<String> getAllPhotographerIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT photographer_id FROM Photographer");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("photographer_id"));
        }
        return ids;
    }
}
