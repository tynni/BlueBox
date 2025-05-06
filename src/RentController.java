import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RentController {
    private RentDAO rentDAO = new RentDAO();
    private Scanner scanner = new Scanner(System.in);

    public void addRent() throws SQLException {
        System.out.print("Movie ID: ");
        int movieID = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer ID: ");
        int customerID = Integer.parseInt(scanner.nextLine());
        System.out.print("Duration (days): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Rent rent = new Rent(0, movieID, customerID, duration, price);
        rentDAO.addRent(rent);
        System.out.println("Rent added.");
    }

    public void viewAllRents() throws SQLException {
        List<Rent> rents = rentDAO.getAllRents();
        for (Rent r : rents) {
            System.out.println("RentID: " + r.getRentID() + ", MovieID: " + r.getMovieID() + ", CustomerID: " + r.getCustomerID());
        }
    }
}
