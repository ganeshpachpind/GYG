package interview.gyg.model;


public class AddReviewViewModel {

    private Review review;

    public AddReviewViewModel(Review review) {
        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void onSubmitReviewClicked() {

    }

}
