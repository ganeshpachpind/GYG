package interview.gyg.model;


import interview.gyg.api.RestAPIFactory;
import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddReviewViewModel implements Observer<ResponseBody> {


    private Review review;
    private RestAPIFactory restAPIFactory;

    public AddReviewViewModel(Review review, RestAPIFactory restAPIFactory) {
        this.review = review;
        this.restAPIFactory = restAPIFactory;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void onSubmitReviewClicked() {
        restAPIFactory.getReviewService()
                .submitReview(new AddReviewRequest(1, review))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
    }
}
