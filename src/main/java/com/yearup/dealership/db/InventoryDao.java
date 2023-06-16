package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory
        String query = "INSERT INTO inventory (vin, dealership_Id) values (?,?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vin);
            statement.setInt(2, dealershipId);
            int rows = statement.executeUpdate();
            System.out.println(rows + " rows affected! ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        String query = "DELETE FROM inventory WHERE vin = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vin);
            int rows = statement.executeUpdate();
            System.out.println(rows + " rows affected! ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
