package interview.gyg.activities;


import interview.gyg.model.ReviewListResponse;

public interface ReviewListView {
    void updateReviewList(ReviewListResponse reviewListResponse);
    void setErrorMessage();
}
