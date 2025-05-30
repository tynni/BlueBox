public class Rent {
    private int rentID;
    private int movieID;
    private int customerID;
    private int duration;
    private double price;

    public Rent() {}

    public Rent(int rentID, int movieID, int customerID, int duration, double price) {
        this.rentID = rentID;
        this.movieID = movieID;
        this.customerID = customerID;
        this.duration = duration;
        this.price = price;
    }

    public int getRentID() { return rentID; }
    public void setRentID(int rentID) { this.rentID = rentID; }

    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
