package interview.gyg.model;


import interview.gyg.activities.AddReviewView;
import interview.gyg.api.RestAPIFactory;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddReviewViewModel implements Observer<Response<ResponseBody>> {


    private Review review;
    private RestAPIFactory restAPIFactory;
    private AddReviewView addReviewView;

    public AddReviewViewModel(Review review, RestAPIFactory restAPIFactory,
                              AddReviewView addReviewView) {
        this.review = review;
        this.restAPIFactory = restAPIFactory;
        this.addReviewView = addReviewView;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void onSubmitReviewClicked() {
        addReviewView.showProgressBar();
        restAPIFactory.getReviewService()
                .submitReview(new AddReviewRequest(1, review))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
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
    public void onNext(Response<ResponseBody> response) {
    }
}
