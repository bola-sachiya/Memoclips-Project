package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.PhotoAlbumDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoAlbumModel {

    public boolean savePhotoAlbum(PhotoAlbumDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO PhotoAlbum (album_id, booking_id, album_type, price) VALUES (?, ?, ?, ?)",
                dto.getAlbumId(),
                dto.getBookingId(),
                dto.getAlbumType(),
                dto.getPrice()
        );
    }

    public boolean updatePhotoAlbum(PhotoAlbumDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE PhotoAlbum SET booking_id = ?, album_type = ?, price = ? WHERE album_id = ?",
                dto.getBookingId(),
                dto.getAlbumType(),
                dto.getPrice(),
                dto.getAlbumId()
        );
    }

    public boolean deletePhotoAlbum(String albumId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM PhotoAlbum WHERE album_id = ?", albumId);
    }

    public PhotoAlbumDto searchPhotoAlbum(String albumId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM PhotoAlbum WHERE album_id = ?", albumId);
        if (rs.next()) {
            return new PhotoAlbumDto(
                    rs.getString("album_id"),
                    rs.getString("booking_id"),
                    rs.getString("album_type"),
                    rs.getString("price")
            );
        }
        return null;
    }

    public ArrayList<PhotoAlbumDto> getAllPhotoAlbums() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM PhotoAlbum");
        ArrayList<PhotoAlbumDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotoAlbumDto(
                    rs.getString("album_id"),
                    rs.getString("booking_id"),
                    rs.getString("album_type"),
                    rs.getString("price")
            ));
        }
        return list;
    }

    public String getNextAlbumId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT album_id FROM PhotoAlbum ORDER BY album_id DESC LIMIT 1");
        char prefix = 'A';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(1);
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

    public ArrayList<String> getAllAlbumIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT album_id FROM PhotoAlbum");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("album_id"));
        }
        return ids;
    }
}
