package interview.gyg.model;


public class Review {
    private int rating;
    private String title;
    private String message;
    private String author;
    private boolean foreignLanguage;

    public Review(int rating, String title, String message, String author, boolean foreignLanguage) {
        this.rating = rating;
        this.title = title;
        this.message = message;
        this.author = author;
        this.foreignLanguage = foreignLanguage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
}
