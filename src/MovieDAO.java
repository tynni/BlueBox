import java.sql.*;
import java.util.*;

public class MovieDAO {
    private Connection connection;

    public MovieDAO() {
        this.connection = DBConnection.getConnection();  // Get connection from DBConnection
    }

    // Create
    public void addMovie(Movie movie) throws SQLException {
        String query = "INSERT INTO Movies (Title, Genre, Year, Production, Length, Description, Cast) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getYear());
            stmt.setString(4, movie.getProduction());
            stmt.setInt(5, movie.getLength());
            stmt.setString(6, movie.getDescription());
            stmt.setString(7, movie.getCast());
            stmt.executeUpdate();
        }
    }

    // Read
    public Movie getMovie(int movieID) throws SQLException {
        String query = "SELECT * FROM Movies WHERE MovieID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, movieID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Movie(
                        rs.getInt("MovieID"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("Year"),
                        rs.getString("Production"),
                        rs.getInt("Length"),
                        rs.getString("Description"),
                        rs.getString("Cast")
                );
            }
            return null;
        }
    }

    // Read all
    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movies";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("MovieID"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("Year"),
                        rs.getString("Production"),
                        rs.getInt("Length"),
                        rs.getString("Description"),
                        rs.getString("Cast")
                ));
            }
        }
        return movies;
    }

    // Update
    public void updateMovie(Movie movie) throws SQLException {
        String query = "UPDATE Movies SET Title = ?, Genre = ?, Year = ?, Production = ?, Length = ?, Description = ?, Cast = ? WHERE MovieID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getYear());
            stmt.setString(4, movie.getProduction());
            stmt.setInt(5, movie.getLength());
            stmt.setString(6, movie.getDescription());
            stmt.setString(7, movie.getCast());
            stmt.setInt(8, movie.getMovieID());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteMovie(int movieID) throws SQLException {
        String query = "DELETE FROM Movies WHERE MovieID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, movieID);
            stmt.executeUpdate();
        }
    }
}
