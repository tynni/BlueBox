import java.sql.*;
import java.util.*;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO() {
        this.connection = DBConnection.getConnection();  // Get connection from DBConnection
    }

    // Create
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (Name, Contact) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContact());
            stmt.executeUpdate();
        }
    }

    // Read
    public Customer getCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM Customers WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Contact")
                );
            }
            return null;
        }
    }

    // Read all
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customers";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Contact")
                ));
            }
        }
        return customers;
    }

    // Update
    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customers SET Name = ?, Contact = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContact());
            stmt.setInt(3, customer.getCustomerID());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteCustomer(int customerID) throws SQLException {
        String query = "DELETE FROM Customers WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            stmt.executeUpdate();
        }
    }
}
