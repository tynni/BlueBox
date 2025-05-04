public class Customer {
    private int customerID;
    private String name;
    private String contact;

    public Customer() {}

    public Customer(int customerID, String name, String contact) {
        this.customerID = customerID;
        this.name = name;
        this.contact = contact;
    }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
