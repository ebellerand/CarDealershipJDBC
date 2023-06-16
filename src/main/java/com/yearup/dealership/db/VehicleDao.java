package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleDao {
    Scanner scanner = new Scanner(System.in);
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        String query = "INSERT INTO vehicles (vin, make, model, year, sold, color, vehicleType, odometer, price) values (?,?,?,?,?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getYear());
            statement.setBoolean(5, vehicle.isSold());
            statement.setString(6, vehicle.getColor());
            statement.setString(7, vehicle.getVehicleType());
            statement.setInt(8, vehicle.getOdometer());
            statement.setDouble(9, vehicle.getPrice());
            int rows = statement.executeUpdate();
            System.out.println(rows + " rows affected! ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        String query = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, VIN);
            int rows = statement.executeUpdate();
            System.out.println(rows + " rows affected! ");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        Vehicle vehicle = null;
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> vehiclesByPriceList;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            try (ResultSet results = statement.executeQuery()) {

                vehiclesByPriceList = new ArrayList<>();
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehiclesByPriceList.add(vehicle);

                }
                //System.out.println(vehiclesByPriceList);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehiclesByPriceList;

    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        Vehicle vehicle = null;
        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        List<Vehicle> vehiclesByMakeModelList;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, make);
            statement.setString(2, model);

            try (ResultSet results = statement.executeQuery()) {
                vehiclesByMakeModelList = new ArrayList<>();
                while (results.next()) {
                    vehicle = createVehicleFromResultSet(results);
                    vehiclesByMakeModelList.add(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } return vehiclesByMakeModelList;
    }
        public List<Vehicle> searchByYearRange ( int minYear, int maxYear) {
            // TODO: Implement the logic to search vehicles by year range
            Vehicle vehicle = null;
            String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
            List<Vehicle> vehiclesByYearList;
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, minYear);
                statement.setInt(2, maxYear);

                try (ResultSet results = statement.executeQuery()) {
                    vehiclesByYearList = new ArrayList<>();
                    while (results.next()) {
                        vehicle = createVehicleFromResultSet(results);
                        vehiclesByYearList.add(vehicle);

                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return vehiclesByYearList;
        }

            public List<Vehicle> searchByColor (String color){
                // TODO: Implement the logic to search vehicles by color
                Vehicle vehicle = null;
                String query = "SELECT * FROM vehicles WHERE color = ?";
                List<Vehicle> vehiclesByColorList;
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, color);

                    try (ResultSet results = statement.executeQuery()) {
                        vehiclesByColorList = new ArrayList<>();
                        while (results.next()) {
                            vehicle = createVehicleFromResultSet(results);
                            vehiclesByColorList.add(vehicle);

                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return vehiclesByColorList;
            }

            public List<Vehicle> searchByMileageRange ( int minMileage, int maxMileage){
                // TODO: Implement the logic to search vehicles by mileage range
                Vehicle vehicle = null;
                String query = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
                List<Vehicle> vehiclesByMileageList;
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, minMileage);
                    statement.setInt(2, maxMileage);

                    try (ResultSet results = statement.executeQuery()) {
                        vehiclesByMileageList = new ArrayList<>();
                        while (results.next()) {
                            vehicle = createVehicleFromResultSet(results);
                            vehiclesByMileageList.add(vehicle);

                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return vehiclesByMileageList;
            }

            public List<Vehicle> searchByType (String type){
                // TODO: Implement the logic to search vehicles by type
                Vehicle vehicle = null;
                String query = "SELECT * FROM vehicles WHERE vehicleType = ?";
                List<Vehicle> vehiclesByVehicleTypeList;
                try (Connection connection = dataSource.getConnection();
                     PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, type);

                    try (ResultSet results = statement.executeQuery()) {
                        vehiclesByVehicleTypeList = new ArrayList<>();
                        while (results.next()) {
                            vehicle = createVehicleFromResultSet(results);
                            vehiclesByVehicleTypeList.add(vehicle);

                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return vehiclesByVehicleTypeList;
            }

        private Vehicle createVehicleFromResultSet (ResultSet resultSet) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setVin(resultSet.getString("VIN"));
            vehicle.setMake(resultSet.getString("make"));
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setYear(resultSet.getInt("year"));
            vehicle.setSold(resultSet.getBoolean("SOLD"));
            vehicle.setColor(resultSet.getString("color"));
            vehicle.setVehicleType(resultSet.getString("vehicleType"));
            vehicle.setOdometer(resultSet.getInt("odometer"));
            vehicle.setPrice(resultSet.getDouble("price"));
            return vehicle;
        }
    }

