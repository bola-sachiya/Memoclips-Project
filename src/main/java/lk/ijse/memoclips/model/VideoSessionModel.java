package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.VideoSessionDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideoSessionModel {

    public boolean saveVideoSession(VideoSessionDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Video_session (video_id, booking_id, videographer_id, duration, format) VALUES (?, ?, ?, ?, ?)",
                dto.getVideoId(),
                dto.getBookingId(),
                dto.getVideographerId(),
                dto.getDuration(),
                dto.getFormat()
        );
    }

    public boolean updateVideoSession(VideoSessionDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Video_session SET booking_id = ?, videographer_id = ?, duration = ?, format = ? WHERE video_id = ?",
                dto.getBookingId(),
                dto.getVideographerId(),
                dto.getDuration(),
                dto.getFormat(),
                dto.getVideoId()
        );
    }

    public boolean deleteVideoSession(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Video_session WHERE video_id = ?", id);
    }

    public VideoSessionDto searchVideoSession(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Video_session WHERE video_id = ?", id);
        if (rs.next()) {
            return new VideoSessionDto(
                    rs.getString("video_id"),
                    rs.getString("booking_id"),
                    rs.getString("videographer_id"),
                    rs.getString("duration"),
                    rs.getString("format")
            );
        }
        return null;
    }

    public ArrayList<VideoSessionDto> getAllVideoSessions() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Video_session");
        ArrayList<VideoSessionDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new VideoSessionDto(
                    rs.getString("video_id"),
                    rs.getString("booking_id"),
                    rs.getString("videographer_id"),
                    rs.getString("duration"),
                    rs.getString("format")
            ));
        }
        return list;
    }

    public String getNextVideoSessionId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT video_id FROM Video_session ORDER BY video_id DESC LIMIT 1");
        char prefix = 'V';

        if (rs.next()) {
            String lastId = rs.getString("video_id");
            String numeric = lastId.substring(1);
            int number = Integer.parseInt(numeric);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }

    public ArrayList<String> getAllVideoSessionIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT video_id FROM Video_session");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("video_id"));
        }
        return ids;
    }
}
