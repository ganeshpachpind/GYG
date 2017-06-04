package interview.gyg.api;


import interview.gyg.model.ReviewListResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ReviewService {

    @GET("reviews.json")
    Observable<ReviewListResponse> getReviewList(@Query("page") int page);
}
