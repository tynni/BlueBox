public class Status {
    private int movieID;      // was statusID / rentID
    private String status;    // enum("Rented","Returned","Overdue")
    private Integer staffID;  // who handled it

    public Status() {}

    public Status(int movieID, String status, Integer staffID) {
        this.movieID = movieID;
        this.status  = status;
        this.staffID = staffID;
    }

    public int getMovieID()       { return movieID; }
    public void setMovieID(int id){ this.movieID = id; }

    public String getStatus()         { return status; }
    public void setStatus(String st)  { this.status = st; }

    public Integer getStaffID()           { return staffID; }
    public void setStaffID(Integer sid)   { this.staffID = sid; }
}