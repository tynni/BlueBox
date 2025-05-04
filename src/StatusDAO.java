import java.sql.*;
import java.util.*;

public class StatusDAO {
    private Connection connection;

    public StatusDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Create
    public void addStatus(Status status) throws SQLException {
        String query = "INSERT INTO Status (RentID, StatusType) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, status.getRentID());
            stmt.setString(2, status.getStatus());  // fixed here
            stmt.executeUpdate();
        }
    }

    // Read
    public Status getStatus(int statusID) throws SQLException {
        String query = "SELECT * FROM Status WHERE StatusID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, statusID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Status(
                        rs.getInt("StatusID"),
                        rs.getInt("RentID"),
                        rs.getString("StatusType")
                );
            }
        }
        return null;
    }

    // Read all
    public List<Status> getAllStatuses() throws SQLException {
        List<Status> statuses = new ArrayList<>();
        String query = "SELECT * FROM Status";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                statuses.add(new Status(
                        rs.getInt("StatusID"),
                        rs.getInt("RentID"),
                        rs.getString("StatusType")
                ));
            }
        }
        return statuses;
    }

    // Update
    public void updateStatus(Status status) throws SQLException {
        String query = "UPDATE Status SET RentID = ?, StatusType = ? WHERE StatusID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, status.getRentID());
            stmt.setString(2, status.getStatus());
            stmt.setInt(3, status.getStatusID());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteStatus(int statusID) throws SQLException {
        String query = "DELETE FROM Status WHERE StatusID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, statusID);
            stmt.executeUpdate();
        }
    }
}
