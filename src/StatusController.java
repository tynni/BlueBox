import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StatusController {
    private StatusDAO statusDAO = new StatusDAO();
    private Scanner scanner = new Scanner(System.in);

    public void addStatus() throws SQLException {
        System.out.print("Rent ID: ");
        int rentID = Integer.parseInt(scanner.nextLine());
        System.out.print("Status (Rented/Returned/Overdue): ");
        String statusType = scanner.nextLine();

        Status status = new Status(0, rentID, statusType);
        statusDAO.addStatus(status);
        System.out.println("Status added.");
    }

    public void viewAllStatuses() throws SQLException {
        List<Status> statuses = statusDAO.getAllStatuses();
        for (Status s : statuses) {
            System.out.println("StatusID: " + s.getStatusID() + ", RentID: " + s.getRentID() + ", Status: " + s.getStatus());
        }
    }
}
