package interview.gyg.api;


import java.util.List;

import interview.gyg.model.Review;
import retrofit2.http.GET;
import rx.Observable;

public interface ReviewService {
    @GET("/review.json")
    Observable<List<Review>> getReviewList();
}
