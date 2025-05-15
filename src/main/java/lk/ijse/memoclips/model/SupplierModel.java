package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.SupplierIdDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public boolean saveSupplier(SupplierIdDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Supplier (supplier_id, name, contact, supply_quantity) VALUES (?, ?, ?, ?)",
                dto.getSupplierId(),
                dto.getName(),
                dto.getContact(),
                dto.getSupplyQuantity()
        );
    }

    public boolean updateSupplier(SupplierIdDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Supplier SET name = ?, contact = ?, supply_quantity = ? WHERE supplier_id = ?",
                dto.getName(),
                dto.getContact(),
                dto.getSupplyQuantity(),
                dto.getSupplierId()
        );
    }

    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Supplier WHERE supplier_id = ?", supplierId);
    }

    public SupplierIdDto searchSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Supplier WHERE supplier_id = ?", supplierId);
        if (rs.next()) {
            return new SupplierIdDto(
                    rs.getString("supplier_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("supply_quantity")
            );
        }
        return null;
    }

    public ArrayList<SupplierIdDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Supplier");
        ArrayList<SupplierIdDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new SupplierIdDto(
                    rs.getString("supplier_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("supply_quantity")
            ));
        }
        return list;
    }

    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT supplier_id FROM Supplier ORDER BY supplier_id DESC LIMIT 1");
        char prefix = 'S';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(1);
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }

    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT supplier_id FROM Supplier");
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("supplier_id"));
        }
        return list;
    }
}
