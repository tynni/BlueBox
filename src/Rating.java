public class Rating {
    private int movieID;
    private int customerID;
    private int rating;

    public Rating() {}

    public Rating(int movieID, int customerID, int rating) {
        this.movieID = movieID;
        this.customerID = customerID;
        this.rating = rating;
    }

    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}
