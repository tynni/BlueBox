import java.sql.*;
import java.util.*;

public class RatingDAO {
    private Connection connection;

    public RatingDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Create (Insert a new rating)
    public void addRating(Rating rating) throws SQLException {
        String query = "INSERT INTO Rating (MovieID, CustomerID, RatingValue) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rating.getMovieID());
            stmt.setInt(2, rating.getCustomerID());
            stmt.setInt(3, rating.getRating());
            stmt.executeUpdate();
        }
    }

    // Read (Get a rating by movieID and customerID)
    public Rating getRating(int movieID, int customerID) throws SQLException {
        String query = "SELECT * FROM Rating WHERE MovieID = ? AND CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, movieID);
            stmt.setInt(2, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Rating(
                        rs.getInt("MovieID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("RatingValue")
                );
            }
        }
        return null;
    }

    // Read all
    public List<Rating> getRatingsByMovie(int movieID) throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT * FROM Rating WHERE MovieID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, movieID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ratings.add(new Rating(
                        rs.getInt("MovieID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("RatingValue")
                ));
            }
        }
        return ratings;
    }

    // Update
    public void updateRating(Rating rating) throws SQLException {
        String query = "UPDATE Rating SET RatingValue = ? WHERE MovieID = ? AND CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rating.getRating());
            stmt.setInt(2, rating.getMovieID());
            stmt.setInt(3, rating.getCustomerID());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteRating(int movieID, int customerID) throws SQLException {
        String query = "DELETE FROM Rating WHERE MovieID = ? AND CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, movieID);
            stmt.setInt(2, customerID);
            stmt.executeUpdate();
        }
    }
}
