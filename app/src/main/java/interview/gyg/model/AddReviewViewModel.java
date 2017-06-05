package interview.gyg.model;


import android.util.Log;

import interview.gyg.activities.AddReviewView;
import interview.gyg.repository.ReviewRepository;
import retrofit2.HttpException;
import rx.Observer;

public class AddReviewViewModel implements Observer<Review> {
    private static final String TAG = AddReviewViewModel.class.getSimpleName();

    private int venueId;
    private Review review;
    private AddReviewView addReviewView;
    private ReviewRepository reviewRepository;

    public AddReviewViewModel(Review review,
                              AddReviewView addReviewView,
                              ReviewRepository reviewRepository, int venueId) {
        this.review = review;
        this.addReviewView = addReviewView;
        this.reviewRepository = reviewRepository;
        this.venueId = venueId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void onSubmitReviewClicked() {
        addReviewView.showProgressBar();
        AddReviewRequest addReviewRequest = new AddReviewRequest(venueId, review);
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
        Log.i(TAG, "Review created" + review);
    }
}
