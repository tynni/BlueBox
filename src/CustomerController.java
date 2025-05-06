import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private CustomerDAO customerDAO = new CustomerDAO();
    private Scanner scanner = new Scanner(System.in);

    public void addCustomer() throws SQLException {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact: ");
        String contact = scanner.nextLine();
        Customer customer = new Customer(0, name, contact);
        customerDAO.addCustomer(customer);
        System.out.println("Customer added.");
    }

    public void viewAllCustomers() throws SQLException {
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer c : customers) {
            System.out.println(c.getCustomerID() + " | " + c.getName() + " | " + c.getContact());
        }
    }
}
