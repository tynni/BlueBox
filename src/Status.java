public class Status {
    private int statusID;
    private int rentID;
    private String status; // "Rented", "Returned", or "Overdue"

    public Status() {}

    public Status(int statusID, int rentID, String status) {
        this.statusID = statusID;
        this.rentID = rentID;
        this.status = status;
    }

    public int getStatusID() { return statusID; }

    public void setStatusID(int statusID) { this.statusID = statusID; }

    public int getRentID() { return rentID; }

    public void setRentID(int rentID) { this.rentID = rentID; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
