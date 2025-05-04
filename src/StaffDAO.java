import java.sql.*;
import java.util.*;

public class StaffDAO {
    private Connection connection;

    public StaffDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Create
    public void addStaff(Staff staff) throws SQLException {
        String query = "INSERT INTO MovieRentalStaff (Name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staff.getName());
            stmt.executeUpdate();
        }
    }

    // Read
    public Staff getStaff(int staffID) throws SQLException {
        String query = "SELECT * FROM MovieRentalStaff WHERE StaffID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, staffID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("Name")
                );
            }
        }
        return null;
    }

    // Read all
    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM MovieRentalStaff";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                staffList.add(new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("Name")
                ));
            }
        }
        return staffList;
    }

    // Update
    public void updateStaff(Staff staff) throws SQLException {
        String query = "UPDATE MovieRentalStaff SET Name = ? WHERE StaffID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staff.getName());
            stmt.setInt(2, staff.getStaffID());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteStaff(int staffID) throws SQLException {
        String query = "DELETE FROM MovieRentalStaff WHERE StaffID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, staffID);
            stmt.executeUpdate();
        }
    }
}
