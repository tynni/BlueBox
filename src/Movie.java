public class Movie {
    private int movieID;
    private String title;
    private String genre;
    private int year;
    private String production;
    private int length;
    private String description;
    private String cast;

    public Movie() {}

    public Movie(int movieID, String title, String genre, int year, String production, int length, String description, String cast) {
        this.movieID = movieID;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.production = production;
        this.length = length;
        this.description = description;
        this.cast = cast;
    }

    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getProduction() { return production; }
    public void setProduction(String production) { this.production = production; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCast() { return cast; }
    public void setCast(String cast) { this.cast = cast; }
}
