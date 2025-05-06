import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StaffController {
    private StaffDAO staffDAO = new StaffDAO();
    private Scanner scanner = new Scanner(System.in);

    public void addStaff() throws SQLException {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        Staff staff = new Staff(0, name);
        staffDAO.addStaff(staff);
        System.out.println("Staff added.");
    }

    public void viewAllStaff() throws SQLException {
        List<Staff> staffList = staffDAO.getAllStaff();
        for (Staff s : staffList) {
            System.out.println("StaffID: " + s.getStaffID() + ", Name: " + s.getName());
        }
    }
}
