package interview.gyg.activities;


import interview.gyg.model.ReviewListResponse;

public interface ReviewListView {
    void updateReviewList(ReviewListResponse reviewListResponse);
    void showErrorMessage();

    void showProgressBar();

    void hideProgressBar();

    void showNetworkErrorMessage();

    void navigateToAddReview();
}
