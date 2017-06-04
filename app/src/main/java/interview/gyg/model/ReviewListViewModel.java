package interview.gyg.model;


import interview.gyg.activities.ReviewListView;
import interview.gyg.repository.ReviewRepository;
import retrofit2.HttpException;
import rx.Observer;

public class ReviewListViewModel implements Observer<ReviewListResponse> {
    private ReviewRepository reviewRepository;
    private ReviewListView reviewListView;

    public ReviewListViewModel(ReviewRepository reviewRepository, ReviewListView reviewListView) {
        this.reviewRepository = reviewRepository;
        this.reviewListView = reviewListView;
    }

    public void getReviewList(int pageNo) {
        reviewRepository.getReviews(pageNo, this);
        reviewListView.showProgressBar();
    }

    @Override
    public void onCompleted() {
        reviewListView.hideProgressBar();
    }

    @Override
    public void onError(Throwable throwable) {
        reviewListView.hideProgressBar();
        if (throwable instanceof HttpException) {
            reviewListView.showErrorMessage();
        } else {
            reviewListView.showNetworkErrorMessage();
        }
    }

    @Override
    public void onNext(ReviewListResponse reviewListResponse) {
        reviewListView.updateReviewList(reviewListResponse);
    }

    public void onAddReviewClicked(){
        reviewListView.navigateToAddReview();

    }
}
