import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StatusController {
    private StatusDAO statusDAO = new StatusDAO();
    private Scanner scanner = new Scanner(System.in);

    public void addStatus() throws SQLException {
        System.out.print("Movie ID: ");
        int movieID = Integer.parseInt(scanner.nextLine());

        System.out.print("Status (Rented/Returned/Overdue): ");
        String statusType = scanner.nextLine();

        System.out.print("Staff ID (who handled this, or leave blank): ");
        String staffInput = scanner.nextLine();
        Integer staffID = staffInput.isBlank() ? null : Integer.parseInt(staffInput);

        // use the upsert so we insert-or-update the row keyed by movieID
        Status status = new Status(movieID, statusType, staffID);
        statusDAO.upsertStatus(status);

        System.out.println("Status for movie " + movieID + " set to “" + statusType + "”.");
    }

    public void viewAllStatuses() throws SQLException {
        List<Status> statuses = statusDAO.getAllStatuses();
        for (Status s : statuses) {
            System.out.println(
                    "MovieID: " + s.getMovieID()
                            + ", Status: "   + s.getStatus()
                            + ", StaffID: "  + (s.getStaffID() != null ? s.getStaffID() : "<none>")
            );
        }
    }
}
