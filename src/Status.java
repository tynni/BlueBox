public class Status {
    private int movieID;
    private String status;  // e.g., "Rented", "Returned", "Overdue"

    public Status() {}

    public Status(int movieID, String status) {
        this.movieID = movieID;
        this.status = status;
    }

    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
