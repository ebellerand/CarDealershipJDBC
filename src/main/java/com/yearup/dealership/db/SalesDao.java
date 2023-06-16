package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        String query = "INSERT INTO sales_contracts (Contract_Id, vin, sale_Date, price) values (?, ?, ?, ?) ";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,salesContract.getContractId());
            statement.setString(2, salesContract.getVin());
            statement.setDate(3, java.sql.Date.valueOf(salesContract.getSaleDate()));
            statement.setDouble(4, salesContract.getPrice());
            int rows = statement.executeUpdate();
            System.out.println(rows + " rows affected! ");

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
