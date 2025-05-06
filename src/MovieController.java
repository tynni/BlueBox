import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MovieController {
    private MovieDAO movieDAO = new MovieDAO();
    private Scanner scanner = new Scanner(System.in);

    public void addMovie() throws SQLException {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Production: ");
        String production = scanner.nextLine();
        System.out.print("Length: ");
        int length = Integer.parseInt(scanner.nextLine());
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Cast: ");
        String cast = scanner.nextLine();

        Movie movie = new Movie(0, title, genre, year, production, length, description, cast);
        movieDAO.addMovie(movie);
        System.out.println("Movie added.");
    }

    public void viewAllMovies() throws SQLException {
        List<Movie> movies = movieDAO.getAllMovies();
        for (Movie m : movies) {
            System.out.println(m.getMovieID() + " | " + m.getTitle() + " | " + m.getGenre());
        }
    }
}
