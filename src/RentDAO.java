import java.sql.*;
import java.util.*;

public class RentDAO {
    private Connection connection;

    public RentDAO() {
        this.connection = DBConnection.getConnection();  // Reuse DBConnection
    }

    // Create
    public void addRent(Rent rent) throws SQLException {
        String query = "INSERT INTO Rents (MovieID, CustomerID, Duration, Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rent.getMovieID());
            stmt.setInt(2, rent.getCustomerID());
            stmt.setInt(3, rent.getDuration());
            stmt.setDouble(4, rent.getPrice());
            stmt.executeUpdate();
        }
    }

    // Read
    public Rent getRent(int rentID) throws SQLException {
        String query = "SELECT * FROM Rents WHERE RentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rentID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Rent(
                        rs.getInt("RentID"),
                        rs.getInt("MovieID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("Duration"),
                        rs.getDouble("Price")
                );
            }
        }
        return null;
    }

    // Read all
    public List<Rent> getAllRents() throws SQLException {
        List<Rent> rents = new ArrayList<>();
        String query = "SELECT * FROM Rents";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rents.add(new Rent(
                        rs.getInt("RentID"),
                        rs.getInt("MovieID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("Duration"),
                        rs.getDouble("Price")
                ));
            }
        }
        return rents;
    }

    // Update
    public void updateRent(Rent rent) throws SQLException {
        String query = "UPDATE Rents SET MovieID = ?, CustomerID = ?, Duration = ?, Price = ? WHERE RentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rent.getMovieID());
            stmt.setInt(2, rent.getCustomerID());
            stmt.setInt(3, rent.getDuration());
            stmt.setDouble(4, rent.getPrice());
            stmt.setInt(5, rent.getRentID());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteRent(int rentID) throws SQLException {
        String query = "DELETE FROM Rents WHERE RentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rentID);
            stmt.executeUpdate();
        }
    }
}
