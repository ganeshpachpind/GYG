package interview.gyg.model;


import interview.gyg.activities.ReviewListView;
import interview.gyg.repository.ReviewRepository;
import rx.Observer;

public class ReviewListViewModel implements Observer<ReviewListResponse> {
    private ReviewRepository reviewRepository;
    private ReviewListView reviewListView;

    public ReviewListViewModel(ReviewRepository reviewRepository, ReviewListView reviewListView) {
        this.reviewRepository = reviewRepository;
        this.reviewListView = reviewListView;
    }

    public void getReviewList() {
        reviewRepository.getReviews(this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        reviewListView.setErrorMessage();
    }

    @Override
    public void onNext(ReviewListResponse reviewListResponse) {
        reviewListView.setListAdapter(reviewListResponse.getReviews());
    }
}
