public class Staff {
    private int staffID;
    private String name;

    public Staff() {}

    public Staff(int staffID, String name) {
        this.staffID = staffID;
        this.name = name;
    }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
