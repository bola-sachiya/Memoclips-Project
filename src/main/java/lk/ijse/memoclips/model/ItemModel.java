package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.ItemDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {

    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO item (item_id, name, quantity, last_updated_date, supplier_id) VALUES (?, ?, ?, ?, ?)",
                dto.getItemId(),
                dto.getItemName(),
                dto.getQuantity(),
                dto.getLastUpdateDate(),
                dto.getSupplierId()
        );
    }

    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE item SET name = ?, quantity = ?, last_updated_date = ?, supplier_id = ? WHERE item_id = ?",
                dto.getItemName(),
                dto.getQuantity(),
                dto.getLastUpdateDate(),
                dto.getSupplierId(),
                dto.getItemId()
        );
    }

    public boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE item_id = ?", itemId);
    }

    public ItemDto searchItem(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE item_id = ?", itemId);
        if (rs.next()) {
            return new ItemDto(
                    rs.getString("item_id"),
                    rs.getString("name"),
                    rs.getString("quantity"),
                    rs.getString("last_updated_date"),
                    rs.getString("supplier_id")
            );
        }
        return null;
    }

    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item");
        ArrayList<ItemDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new ItemDto(
                    rs.getString("item_id"),
                    rs.getString("name"),
                    rs.getString("quantity"),
                    rs.getString("last_updated_date"),
                    rs.getString("supplier_id")
            ));
        }
        return list;
    }

    public String getNextItemId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT item_id FROM item ORDER BY item_id DESC LIMIT 1");
        char prefix = 'I';

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(1);
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT item_id FROM item");
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }
}
