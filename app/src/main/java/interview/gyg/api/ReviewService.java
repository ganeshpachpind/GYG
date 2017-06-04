package interview.gyg.api;


import interview.gyg.model.ReviewListResponse;
import retrofit2.http.GET;
import rx.Observable;

public interface ReviewService {

    @GET("reviews.json")
    Observable<ReviewListResponse> getReviewList();
}
