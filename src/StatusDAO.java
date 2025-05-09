import java.sql.*;
import java.util.*;

public class StatusDAO {
    private Connection connection = DBConnection.getConnection();

    // Create or overwrite status for a given movie
    public void upsertStatus(Status s) throws SQLException {
        // if a row for this movieID already exists, update it; otherwise insert
        String sql =
                "INSERT INTO Status (MovieID, Status, StaffID) VALUES (?,?,?) " +
                        "ON DUPLICATE KEY UPDATE Status = VALUES(Status), StaffID = VALUES(StaffID)";
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setInt(1, s.getMovieID());
            p.setString(2, s.getStatus());
            if (s.getStaffID() != null) p.setInt(3, s.getStaffID());
            else                        p.setNull(3, Types.INTEGER);
            p.executeUpdate();
        }
    }

    // Read one status by movieID
    public Status getStatus(int movieID) throws SQLException {
        String sql = "SELECT MovieID, Status, StaffID FROM Status WHERE MovieID = ?";
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setInt(1, movieID);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    return new Status(
                            rs.getInt("MovieID"),
                            rs.getString("Status"),
                            rs.getObject("StaffID") == null ? null : rs.getInt("StaffID")
                    );
                }
            }
        }
        return null;
    }

    // Read all statuses
    public List<Status> getAllStatuses() throws SQLException {
        List<Status> list = new ArrayList<>();
        String sql = "SELECT MovieID, Status, StaffID FROM Status";
        try (PreparedStatement p = connection.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                list.add(new Status(
                        rs.getInt("MovieID"),
                        rs.getString("Status"),
                        rs.getObject("StaffID") == null ? null : rs.getInt("StaffID")
                ));
            }
        }
        return list;
    }

    // Delete status for a movie
    public void deleteStatus(int movieID) throws SQLException {
        String sql = "DELETE FROM Status WHERE MovieID = ?";
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setInt(1, movieID);
            p.executeUpdate();
        }
    }
}
