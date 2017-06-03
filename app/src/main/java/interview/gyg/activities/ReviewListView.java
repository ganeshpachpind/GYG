package interview.gyg.activities;


import java.util.List;

import interview.gyg.model.Review;

public interface ReviewListView {
    void setListAdapter(List<Review> reviews);
    void setErrorMessage();
}
