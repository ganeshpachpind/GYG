package interview.gyg.repository;


import interview.gyg.api.RestAPIFactory;
import interview.gyg.model.AddReviewRequest;
import interview.gyg.model.Review;
import interview.gyg.model.ReviewListResponse;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReviewRepository {

    private final RestAPIFactory restAPIFactory;

    public ReviewRepository(RestAPIFactory restAPIFactory) {
        this.restAPIFactory = restAPIFactory;
    }

    public void getReviews(int pageNo, Observer<ReviewListResponse> observer) {
        restAPIFactory.getReviewService()
                .getReviewList(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void saveReview(AddReviewRequest addReviewRequest, Observer<Review> callback) {
        restAPIFactory.getReviewService()
                .submitReview(addReviewRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }
}
