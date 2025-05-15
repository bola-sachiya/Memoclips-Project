package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.AssistantDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssistantModel {

    public boolean saveAssistant(AssistantDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Assistant (assistant_id, photographer_id, name, availability) VALUES (?, ?, ?, ?)",
                dto.getAssistantId(),
                dto.getPhotographerId(),
                dto.getAssistantName(),
                dto.getAvailability()
        );
    }

    public boolean updateAssistant(AssistantDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Assistant SET photographer_id = ?, name = ?, availability = ? WHERE assistant_id = ?",
                dto.getPhotographerId(),
                dto.getAssistantName(),
                dto.getAvailability(),
                dto.getAssistantId()
        );
    }

    public boolean deleteAssistant(String assistantId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "DELETE FROM Assistant WHERE assistant_id = ?",
                assistantId
        );
    }

    public AssistantDto searchAssistant(String assistantId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM Assistant WHERE assistant_id = ?",
                assistantId
        );

        if (rs.next()) {
            return new AssistantDto(
                    rs.getString("assistant_id"),
                    rs.getString("name"),
                    rs.getString("photographer_id"),
                    rs.getString("availability")
            );
        }
        return null;
    }

    public ArrayList<AssistantDto> getAllAssistants() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Assistant");
        ArrayList<AssistantDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new AssistantDto(
                    rs.getString("assistant_id"),
                    rs.getString("name"),
                    rs.getString("photographer_id"),
                    rs.getString("availability")
            ));
        }

        return list;
    }

    public String getNextAssistantId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT assistant_id FROM Assistant ORDER BY assistant_id DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int number = Integer.parseInt(lastId.replaceAll("[^0-9]", ""));
            return String.format("A%03d", number + 1);
        }
        return "A001";
    }

    public ArrayList<String> getAllAssistantIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT assistant_id FROM Assistant");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString(1));
        }
        return ids;
    }
}
