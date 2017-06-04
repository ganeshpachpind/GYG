package interview.gyg.model;


public class AddReviewRequest {
    private int venueId;
    private Review review;

    public AddReviewRequest(int venueId, Review review) {
        this.venueId = venueId;
        this.review = review;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
