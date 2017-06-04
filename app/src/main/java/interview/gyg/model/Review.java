package interview.gyg.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
    private double rating;
    private String title;
    private String message;
    private String author;
    private boolean foreignLanguage;
    private String country;

    public Review() {
    }

    public Review(double rating, String title, String message, String author, boolean foreignLanguage) {
        this.rating = rating;
        this.title = title;
        this.message = message;
        this.author = author;
        this.foreignLanguage = foreignLanguage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(boolean foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
