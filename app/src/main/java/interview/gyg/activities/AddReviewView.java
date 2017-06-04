package interview.gyg.activities;


public interface AddReviewView {
    void showErrorMessage();

    void showNetworkErrorMessage();

    void closeActivityWithSuccessMessage();

    void hideProgressBar();

    void showProgressBar();
}
