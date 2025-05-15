package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.AdminDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminModel {

    public boolean saveAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Admin (admin_id, user_name, password, role) VALUES (?, ?, ?, ?)",
                adminDto.getAdminId(),
                adminDto.getName(),
                adminDto.getPassword(),
                adminDto.getRole()
        );
    }

    public boolean updateAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Admin SET user_name = ?, password = ?, role = ? WHERE admin_id = ?",
                adminDto.getName(),
                adminDto.getPassword(),
                adminDto.getRole(),
                adminDto.getAdminId()
        );
    }

    public boolean deleteAdmin(String adminId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Admin WHERE admin_id = ?", adminId);
    }

    public AdminDto searchAdmin(String adminId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Admin WHERE admin_id = ?", adminId);
        if (rs.next()) {
            return new AdminDto(
                    rs.getString("admin_id"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getString("role")
            );
        }
        return null;
    }

    public ArrayList<AdminDto> getAllAdmins() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Admin");
        ArrayList<AdminDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new AdminDto(
                    rs.getString("admin_id"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getString("role")
            ));
        }
        return list;
    }

    public String getNextAdminId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT admin_id FROM Admin ORDER BY admin_id DESC LIMIT 1");
        char prefix = 'U';  // assuming USR001 format
        if (rs.next()) {
            String lastId = rs.getString("admin_id");
            int num = Integer.parseInt(lastId.substring(3));  // skip "USR"
            int nextId = num + 1;
            return String.format("USR%03d", nextId);
        }
        return "USR001";
    }

    public ArrayList<String> getAllAdminIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT admin_id FROM Admin");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("admin_id"));
        }
        return ids;
    }

    public String findAdminNameById(String adminId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT user_name FROM Admin WHERE admin_id = ?", adminId);
        if (rs.next()) {
            return rs.getString("user_name");
        }
        return null;
    }

    public boolean checkAdminLogin(String username, String password) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM Admin WHERE user_name = ? AND password = ?",
                username,
                password
        );
        return rs.next();  // returns true if user exists
    }
}
