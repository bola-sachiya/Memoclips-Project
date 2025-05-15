package lk.ijse.memoclips.model;

import lk.ijse.memoclips.dto.CustomerDto;
import lk.ijse.memoclips.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException , ClassNotFoundException{
        return CrudUtil.execute(
                "INSERT INTO Customer VALUES(?,?,?,?,?,?)",
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getContactNo(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getAdminId()
        );
    }
    public boolean updateCustomer(CustomerDto customerDto) throws ClassNotFoundException , SQLException{
        return CrudUtil.execute(
                "UPDATE Customer SET customer_name = ? , customer_contact = ? , customer_address = ? WHERE customer_id = ?",
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getContactNo(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getAdminId()
        );
    }

    public boolean deleteCustomer(String custId) throws ClassNotFoundException , SQLException{
        return CrudUtil.execute("DELETE FROM Customer WHERE customer_id = ?",
                custId
        );
    }

    public CustomerDto searchCustomer(String custId) throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE customer_id = ?",
                custId
        );
        if(resultSet.next()){
            CustomerDto dto = new CustomerDto(
                    resultSet.getString("customer_id"),
                    resultSet.getString("customer_name"),
                    resultSet.getString("customer_contact"),
                    resultSet.getString("customer_address"),
                    resultSet.getString("email"),
                    resultSet.getString("admin_id")
            );
            return dto;
        }
        return null;
    }

    public ArrayList<CustomerDto> getAllCustomer() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDto> customerDtoArrayList = new ArrayList<>();

        while (resultSet.next()){
            CustomerDto customerDto = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            customerDtoArrayList.add(customerDto);
        }
        return customerDtoArrayList;
    }

    public String getNextCustomerId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT 1");
        char tableChartacter = 'C';

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChartacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableChartacter + "001";
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("SELECT customer_id FROM Customer");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()){
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }



}
