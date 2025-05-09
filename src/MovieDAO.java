import java.sql.*;
import java.util.*;

public class MovieDAO {
    private final Connection connection;

    public MovieDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Create
    public void addMovie(Movie movie) throws SQLException {
        String sql = """
            INSERT INTO Movies
              (Title, Production, Length, Description, Cast)
            VALUES (?,?,?,?,?)
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getProduction());
            stmt.setInt(3, movie.getLength());
            stmt.setString(4, movie.getDescription());
            stmt.setString(5, movie.getCast());
            stmt.executeUpdate();
        }
    }

    // Read one
    public Movie getMovie(int movieID) throws SQLException {
        String sql = "SELECT MovieID, Title, Production, Length, Description, Cast FROM Movies WHERE MovieID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        }
    }

    // Read all
    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT MovieID, Title, Production, Length, Description, Cast FROM Movies";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(mapRow(rs));
            }
        }
        return movies;
    }

    // Update
    public void updateMovie(Movie movie) throws SQLException {
        String sql = """
            UPDATE Movies SET
              Title       = ?,
              Production  = ?,
              Length      = ?,
              Description = ?,
              Cast        = ?
            WHERE MovieID = ?
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getProduction());
            stmt.setInt(3, movie.getLength());
            stmt.setString(4, movie.getDescription());
            stmt.setString(5, movie.getCast());
            stmt.setInt(6, movie.getMovieID());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteMovie(int movieID) throws SQLException {
        String sql = "DELETE FROM Movies WHERE MovieID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieID);
            stmt.executeUpdate();
        }
    }

    // Helper to map a ResultSet row into your 6‑arg Movie constructor
    private Movie mapRow(ResultSet rs) throws SQLException {
        return new Movie(
                rs.getInt("MovieID"),
                rs.getString("Title"),
                rs.getString("Production"),
                rs.getInt("Length"),
                rs.getString("Description"),
                rs.getString("Cast")
        );
    }
}
