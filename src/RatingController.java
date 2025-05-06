import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RatingController {
    private RatingDAO ratingDAO = new RatingDAO();
    private Scanner scanner = new Scanner(System.in);

    public void addRating() throws SQLException {
        System.out.print("Movie ID: ");
        int movieID = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer ID: ");
        int customerID = Integer.parseInt(scanner.nextLine());
        System.out.print("Rating (1-5): ");
        int ratingValue = Integer.parseInt(scanner.nextLine());

        Rating rating = new Rating(movieID, customerID, ratingValue);
        ratingDAO.addRating(rating);
        System.out.println("Rating added.");
    }

    public void viewRatingsForMovie() throws SQLException {
        System.out.print("Enter Movie ID: ");
        int movieID = Integer.parseInt(scanner.nextLine());

        List<Rating> ratings = ratingDAO.getRatingsByMovie(movieID);
        for (Rating r : ratings) {
            System.out.println("CustomerID: " + r.getCustomerID() + ", Rating: " + r.getRating());
        }
    }
}
