package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.AlbumDetailsDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumDetailsModel {

    public boolean saveAlbumDetail(AlbumDetailsDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Album_details (detail_id, album_id, number_of_photos, cover_type, size, paper_quantity) VALUES (?, ?, ?, ?, ?, ?)",
                dto.getDetailId(),
                dto.getAlbumId(),
                Integer.parseInt(dto.getNumberOfPhotos()),
                dto.getCoverType(),
                dto.getSize(),
                Integer.parseInt(dto.getPaperQuantity())
        );
    }

    public boolean updateAlbumDetail(AlbumDetailsDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Album_details SET album_id = ?, number_of_photos = ?, cover_type = ?, size = ?, paper_quantity = ? WHERE detail_id = ?",
                dto.getAlbumId(),
                Integer.parseInt(dto.getNumberOfPhotos()),
                dto.getCoverType(),
                dto.getSize(),
                Integer.parseInt(dto.getPaperQuantity()),
                dto.getDetailId()
        );
    }

    public boolean deleteAlbumDetail(String detailId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "DELETE FROM Album_details WHERE detail_id = ?",
                detailId
        );
    }

    public AlbumDetailsDto searchAlbumDetail(String detailId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM Album_details WHERE detail_id = ?",
                detailId
        );

        if (rs.next()) {
            return new AlbumDetailsDto(
                    rs.getString("detail_id"),
                    rs.getString("album_id"),
                    String.valueOf(rs.getInt("number_of_photos")),
                    rs.getString("cover_type"),
                    rs.getString("size"),
                    String.valueOf(rs.getInt("paper_quantity"))
            );
        }
        return null;
    }

    public ArrayList<AlbumDetailsDto> getAllAlbumDetails() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Album_details");
        ArrayList<AlbumDetailsDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new AlbumDetailsDto(
                    rs.getString("detail_id"),
                    rs.getString("album_id"),
                    String.valueOf(rs.getInt("number_of_photos")),
                    rs.getString("cover_type"),
                    rs.getString("size"),
                    String.valueOf(rs.getInt("paper_quantity"))
            ));
        }
        return list;
    }

    public String getNextDetailId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT detail_id FROM Album_details ORDER BY detail_id DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString("detail_id");
            int number = Integer.parseInt(lastId.replaceAll("[^0-9]", ""));
            return String.format("D%03d", number + 1);
        }
        return "D001";
    }

    public ArrayList<String> getAllDetailIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT detail_id FROM Album_details");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("detail_id"));
        }
        return ids;
    }
}

