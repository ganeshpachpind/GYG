package interview.gyg.model;


import interview.gyg.activities.AddReviewView;
import interview.gyg.repository.ReviewRepository;
import retrofit2.HttpException;
import rx.Observer;

public class AddReviewViewModel implements Observer<Review> {


    private Review review;
    private AddReviewView addReviewView;
    private ReviewRepository reviewRepository;

    public AddReviewViewModel(Review review,
                              AddReviewView addReviewView,
                              ReviewRepository reviewRepository) {
        this.review = review;
        this.addReviewView = addReviewView;
        this.reviewRepository = reviewRepository;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void onSubmitReviewClicked() {
        addReviewView.showProgressBar();
        AddReviewRequest addReviewRequest = new AddReviewRequest(1, review);
        reviewRepository.saveReview(addReviewRequest, this);
    }

    @Override
    public void onCompleted() {
        addReviewView.hideProgressBar();
        addReviewView.closeActivityWithSuccessMessage();
    }

    @Override
    public void onError(Throwable throwable) {
        addReviewView.hideProgressBar();
        if (throwable instanceof HttpException) {
            addReviewView.showErrorMessage();
        } else {
            addReviewView.showNetworkErrorMessage();
        }
    }

    @Override
    public void onNext(Review review) {
        System.out.println("Review submitted is :" + review);
    }
}
