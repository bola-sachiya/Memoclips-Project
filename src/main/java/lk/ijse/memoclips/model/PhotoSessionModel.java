package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.PhotoSessionDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoSessionModel {

    public boolean savePhotoSession(PhotoSessionDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Photo_session (session_id, booking_id, photographer_id, session_type, duration) VALUES (?, ?, ?, ?, ?)",
                dto.getSessionId(),
                dto.getBookId(),
                dto.getPhotographerId(),
                dto.getSessionType(),
                dto.getDuration()
        );
    }

    public boolean updatePhotoSession(PhotoSessionDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Photo_session SET booking_id = ?, photographer_id = ?, session_type = ?, duration = ? WHERE session_id = ?",
                dto.getBookId(),
                dto.getPhotographerId(),
                dto.getSessionType(),
                dto.getDuration(),
                dto.getSessionId()
        );
    }

    public boolean deletePhotoSession(String sessionId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Photo_session WHERE session_id = ?", sessionId);
    }

    public PhotoSessionDto searchPhotoSession(String sessionId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Photo_session WHERE session_id = ?", sessionId);
        if (rs.next()) {
            return new PhotoSessionDto(
                    rs.getString("session_id"),
                    rs.getString("booking_id"),
                    rs.getString("photographer_id"),
                    rs.getString("session_type"),
                    rs.getString("duration")
            );
        }
        return null;
    }

    public ArrayList<PhotoSessionDto> getAllPhotoSessions() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Photo_session");
        ArrayList<PhotoSessionDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotoSessionDto(
                    rs.getString("session_id"),
                    rs.getString("booking_id"),
                    rs.getString("photographer_id"),
                    rs.getString("session_type"),
                    rs.getString("duration")
            ));
        }
        return list;
    }

    public String getNextSessionId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT session_id FROM Photo_session ORDER BY session_id DESC LIMIT 1");
        char prefix = 'S';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(1);
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

    public ArrayList<String> getAllSessionIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT session_id FROM Photo_session");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("session_id"));
        }
        return ids;
    }
}
