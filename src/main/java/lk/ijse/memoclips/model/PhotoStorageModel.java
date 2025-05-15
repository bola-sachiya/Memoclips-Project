package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.PhotoStorageDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoStorageModel {

    public boolean savePhoto(PhotoStorageDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Photo_storage (photo_id, session_id, upload_date, upload_time) VALUES (?, ?, ?, ?)",
                dto.getPhotoId(),
                dto.getSessionId(),
                dto.getUploadDate(),
                dto.getUploadTime()
        );
    }

    public boolean updatePhoto(PhotoStorageDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Photo_storage SET session_id = ?, upload_date = ?, upload_time = ? WHERE photo_id = ?",
                dto.getSessionId(),
                dto.getUploadDate(),
                dto.getUploadTime(),
                dto.getPhotoId()
        );
    }

    public boolean deletePhoto(String photoId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Photo_storage WHERE photo_id = ?", photoId);
    }

    public PhotoStorageDto searchPhoto(String photoId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Photo_storage WHERE photo_id = ?", photoId);
        if (rs.next()) {
            return new PhotoStorageDto(
                    rs.getString("photo_id"),
                    rs.getString("session_id"),
                    rs.getString("upload_date"),
                    rs.getString("upload_time")
            );
        }
        return null;
    }

    public ArrayList<PhotoStorageDto> getAllPhotos() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Photo_storage");
        ArrayList<PhotoStorageDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotoStorageDto(
                    rs.getString("photo_id"),
                    rs.getString("session_id"),
                    rs.getString("upload_date"),
                    rs.getString("upload_time")
            ));
        }
        return list;
    }

    public String getNextPhotoId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT photo_id FROM Photo_storage ORDER BY photo_id DESC LIMIT 1");
        char prefix = 'P';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(1); // remove prefix
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }

    public ArrayList<String> getAllPhotoIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT photo_id FROM Photo_storage");
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("photo_id"));
        }
        return list;
    }
}
