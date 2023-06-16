package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.rmi.dgc.Lease;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        String query = "INSERT INTO sales_contracts (Contract_Id, vin, lease_start, lease_end, monthly_payment ) values (?, ?, ?, ?, ?) ";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,leaseContract.getContractId());
            statement.setString(2, leaseContract.getVin());
            statement.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseStart()));
            statement.setDate(4, java.sql.Date.valueOf(leaseContract.getLeaseEnd()));
            statement.setDouble(5, leaseContract.getMonthlyPayment());
            int rows = statement.executeUpdate();
            System.out.println(rows + " rows affected! ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
