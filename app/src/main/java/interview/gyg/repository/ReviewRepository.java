package interview.gyg.repository;


import java.util.List;

import interview.gyg.api.RestAPIFactory;
import interview.gyg.model.Review;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReviewRepository {

    private final RestAPIFactory restAPIFactory;

    public ReviewRepository(RestAPIFactory restAPIFactory) {
        this.restAPIFactory = restAPIFactory;
    }

    public void getReviews(Subscriber<List<Review>> subscriber) {
        Subscription subscription = restAPIFactory.getReviewService()
                .getReviewList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
