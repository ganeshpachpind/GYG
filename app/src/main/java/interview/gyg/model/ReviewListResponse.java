package interview.gyg.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewListResponse {
    @JsonProperty("status")
    private boolean status;

    @JsonProperty("totalReviews")
    private int totalReviews;

    @JsonProperty("data")
    private List<Review> reviews;

    public ReviewListResponse() {
    }

    public ReviewListResponse(boolean status, int totalReviews, List<Review> reviews) {
        this.status = status;
        this.totalReviews = totalReviews;
        this.reviews = reviews;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
